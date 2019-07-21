package com.example.adrianofpinheiro.jokenpokemon

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.adrianofpinheiro.jokenpokemon.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_criar_conta.*

class CriarContaActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_conta)

        mAuth = FirebaseAuth.getInstance()



        btCriarConta.setOnClickListener {
            if (!edEmail.text.isNullOrEmpty() && !edSenha.text.isNullOrEmpty()) {
                val dialog = ProgressDialog.show(
                        this, getString(R.string.app_name),
                        "Criando usuário...", false, true
                )
                mAuth.createUserWithEmailAndPassword(
                        edEmail.text.toString(),
                        edSenha.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        saveInRealTimeDatabase()
                        dialog.dismiss()
                    } else {
                        dialog.dismiss()
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                edEmail.error = getString(R.string.msg_error_form_email)
                edSenha.error = getString(R.string.msg_error_form_password)
            }
        }
    }

    private fun saveInRealTimeDatabase() {
        val user = Usuario(edEmail.text.toString(),
                edNome.text.toString(),
                edNick.text.toString(),
                spSexo.selectedItem.toString())
        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .setValue(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        val returnIntent = Intent()
                        returnIntent.putExtra("email", edEmail.text.toString())
                        setResult(RESULT_OK, returnIntent)
                        Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {

                        // Toast.makeText(this, "Erro ao criar o usuário", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}
