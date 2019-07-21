package com.example.adrianofpinheiro.jokenpokemon

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private val newUserRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            goToMenu()
        }

        btLogin.setOnClickListener {
            val dialog = ProgressDialog.show(
                    this@LoginActivity, getString(R.string.app_name),
                    "Aguarde...", false, true
            )
            mAuth.signInWithEmailAndPassword(
                    edEmail.text.toString(),
                    edSenha.text.toString()

            ).addOnCompleteListener {

                if (it.isSuccessful) {
                    dialog.dismiss()
                    goToMenu()
                } else {
                    Toast.makeText(this@LoginActivity, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        btNovaConta.setOnClickListener {
            startActivityForResult(Intent(this, CriarContaActivity::class.java), newUserRequestCode)
        }
    }

    private fun goToMenu() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }


}
