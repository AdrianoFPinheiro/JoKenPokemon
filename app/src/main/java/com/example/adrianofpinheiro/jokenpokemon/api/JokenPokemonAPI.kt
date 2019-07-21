package com.example.adrianofpinheiro.jokenpokemon.api


import com.example.adrianofpinheiro.jokenpokemon.model.Jogador
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JokenPokemonAPI {

    @GET("/jokenpokemon/pontuacao")
    fun buscarPontuacao(): Call<List<Jogador>>

    @POST("/jokenpokemon/pontuacao")
    fun enviarPontos(@Body jogador: Jogador): Call<Void>
}