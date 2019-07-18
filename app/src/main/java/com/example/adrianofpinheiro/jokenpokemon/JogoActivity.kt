package com.example.adrianofpinheiro.jokenpokemon

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_jogo.*
import java.util.*

class JogoActivity : AppCompatActivity() {

    private var numeroAleatorio: Random? = null

    private val CHARIZARD = 1
    private val BLASTOISE = 2
    private val VENUSAUR = 3

    var pontuacao = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        numeroAleatorio = Random()

        btCharizand.setOnClickListener {
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.charizard))
            realizarJogada(CHARIZARD)
        }
        btBlastoise.setOnClickListener {
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bastoise))
            realizarJogada(BLASTOISE)
        }
        btVenusaur.setOnClickListener {
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.venusaur))
            realizarJogada(VENUSAUR)
        }


    }
    private fun realizarJogada(jogadaPlayer: Int){

        val player = MediaPlayer.create(this,R.raw.jokenpo)

        player.start()

        val jogadaPC = numeroAleatorio!!.nextInt(3) + 1

        when(jogadaPC){
            CHARIZARD ->{
                ivJogadaIA!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.charizard))
                when(jogadaPlayer){
                    VENUSAUR-> venceu()
                    CHARIZARD -> empatou()
                    BLASTOISE -> perdeu()
                }
            }

            VENUSAUR ->{
                ivJogadaIA!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.charizard))
                when(jogadaPlayer){
                    VENUSAUR-> empatou()
                    CHARIZARD -> perdeu()
                    BLASTOISE -> venceu()
                }
            }

            BLASTOISE ->{
                ivJogadaIA!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.charizard))
                when(jogadaPlayer){
                    VENUSAUR-> perdeu()
                    CHARIZARD -> venceu()
                    BLASTOISE -> empatou()
                }
            }
        }
    }
    fun venceu(){
        pontuacao += 3
        tvPontuacao!!.text = pontuacao.toString()

    }
    private  fun  perdeu(){
        tvPontuacao!!.text = pontuacao.toString()
        val intent = Intent(this@JogoActivity,FimDeJogoActivity::class.java)
        var pontuacao = pontuacao.toString()
        intent.putExtra("Jogador", pontuacao)
        startActivity(intent)

    }
    private fun empatou(){
        pontuacao += 1
        tvPontuacao!!.text = pontuacao.toString()

    }

}
