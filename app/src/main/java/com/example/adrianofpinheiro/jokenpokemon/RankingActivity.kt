package com.example.adrianofpinheiro.jokenpokemon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.adrianofpinheiro.jokenpokemon.api.UsuarioAdapter
import com.example.adrianofpinheiro.jokenpokemon.model.Jogador
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

       // val usuarios: List<Jogador> =
        val jogadores = jogadoresMock()
        rvListaUsuarios.adapter = UsuarioAdapter(jogadores, this, {
            Log.i("TAG", "MEU ITEM")
        })

        val layoutManager = LinearLayoutManager(this)
        rvListaUsuarios.layoutManager = layoutManager

   }

    private fun jogadoresMock(): List<Jogador> {
        return listOf(
                Jogador(
                        1111111,
                        "Jogador Mock",
                        8
                )

        )
    }
}
