package com.example.adrianofpinheiro.jokenpokemon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.adrianofpinheiro.jokenpokemon.api.PontuacaoService
import com.example.adrianofpinheiro.jokenpokemon.api.PontuacaoService.getJokenPokemonAPI
import com.example.adrianofpinheiro.jokenpokemon.model.Jogador
import kotlinx.android.synthetic.main.activity_fim_de_jogo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        btJogarNovamente.setOnClickListener {
            val pontuacao = Jogador(null,
                    etNome.text.toString(),
                    tvPontuacao.text.toString())

            getJokenPokemonAPI()
                    .enviarPontos(pontuacao)
                    .enqueue(object : Callback<Void> {
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            exibirMensagemErro()
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if (response?.isSuccessful == true) {
                                exibirMensagemSucesso()
                            } else {
                                exibirMensagemErro()
                            }
                        }
                    })
            startActivity(Intent(this, JogoActivity::class.java))
        }

    }

    private fun exibirMensagemSucesso() {
        Toast.makeText(this, "Gravado com sucesso", Toast.LENGTH_LONG).show()
    }

    private fun exibirMensagemErro() {
        Toast.makeText(this, "Deu erro ao salvar", Toast.LENGTH_LONG).show()
    }

}