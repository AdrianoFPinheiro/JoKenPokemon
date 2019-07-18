package com.example.adrianofpinheiro.jokenpokemon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.adrianofpinheiro.jokenpokemon.api.PontuacaoService
import kotlinx.android.synthetic.main.activity_fim_de_jogo.*

class FimDeJogoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fim_de_jogo)

        PontuacaoService.getJokenPokemonAPI()

        btJogarNovamente.setOnClickListener{
            startActivity(Intent(this, JogoActivity::class.java))
        }

        btMenuPrincipal.setOnClickListener{
            startActivity(Intent(this, MenuPrincipalActivity::class.java))
        }

        var pontuacaoFinal: String = intent.getStringExtra("Jogador")
        tvPontuacao!!.text = pontuacaoFinal

    }

}