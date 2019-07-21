package com.example.adrianofpinheiro.jokenpokemon.api

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adrianofpinheiro.jokenpokemon.R
import com.example.adrianofpinheiro.jokenpokemon.model.Jogador
import kotlinx.android.synthetic.main.usuario_item.view.*

class UsuarioAdapter(private val jogadores: List<Jogador>,
                     private val context: Context,
                     val listener: (Jogador) -> Unit) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

//Método que recebe o ViewHolder e a posição da lista.
//Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jogador= jogadores[position]
        holder.let {
            it.bindView(jogador, listener)
        }
    }

    //Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.usuario_item, parent, false)
        return ViewHolder(view)
    }
    //Método que deverá retornar quantos itens há na lista.
    override fun getItemCount(): Int {
        return jogadores.size
    }
    /*
    Com o ViewHolder iremos relacionar o layout criado e adicionar os valores a ele*/
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(jogador: Jogador,
                     listener: (Jogador) -> Unit) = with(itemView) {
            val fieldJogador = tvJogador
            val fieldPontos = tvPontos
            fieldJogador.text = jogador.nome
            fieldPontos.text = jogador.pontos.toString()


            setOnClickListener { listener(jogador) }
        }
    }

}