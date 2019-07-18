package com.example.adrianofpinheiro.jokenpokemon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu_principal.*
import org.jetbrains.anko.alert

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        btJogar.setOnClickListener{
            startActivity(Intent(this, JogoActivity::class.java))
        }

        btRanking.setOnClickListener {
            startActivity(Intent(this, RankingActivity::class.java ))
        }

        btSobre.setOnClickListener{
            startActivity(Intent(this, SobreActivity::class.java))
        }

        btSair.setOnClickListener{
            alert(R.string.msg_confirma_logout, R.string.app_name) {
                positiveButton(R.string.sim) {
                    // Confirmou em deslogar
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this@MenuPrincipalActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                negativeButton(R.string.nao) {
                    // Não confirmou...
                }
            }.show()
        }


    }

}