package com.example.adrianofpinheiro.jokenpokemon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.adrianofpinheiro.jokenpokemon.api.PontuacaoService.getJokenPokemonAPI
import com.example.adrianofpinheiro.jokenpokemon.adapter.UsuarioAdapter
import com.example.adrianofpinheiro.jokenpokemon.model.Jogador
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.usuario_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

       val jogadores = jogadoresMock()
       // val jogadores = pegaJogador()

        rvListaUsuarios.adapter = UsuarioAdapter(jogadores, this, {
            Log.i("TAG", "MEU ITEM")
        })

        val layoutManager = LinearLayoutManager(this)
        rvListaUsuarios.layoutManager = layoutManager

    }

    private fun jogadoresMock(): List<Jogador> {
        return listOf(
                Jogador("huifdashfiusa",
                        "Mad Max",
                        "70"),
                Jogador("321421dsadfa",
                        "Wally",
                        "30"),
                Jogador("32rewwrewdfa",
                        "Zeuz",
                        "20"),
                Jogador("32gfsagsfaa",
                        "...",
                        "100")
        )
    }

    private fun pegaJogador() {

        val buscarPontuacao = getJokenPokemonAPI()
                .buscarPontuacao()
                .enqueue(object : Callback<List<Jogador>> {
                    override fun onFailure(call: Call<List<Jogador>>?, t: Throwable?) {

                    }

                    override fun onResponse(call: Call<List<Jogador>>?, response: Response<List<Jogador>>?) {
                        val pontuacao = response?.body()
                        for (pont in pontuacao!!) {
                           tvPontos.text = pont?.pontos
                            Log.i("PONTUACAO", "${pont.nome} - ${pont.pontos}")
                        }
                    }
                })

    }
}
