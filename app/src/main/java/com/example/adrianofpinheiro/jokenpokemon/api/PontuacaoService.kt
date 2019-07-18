package com.example.adrianofpinheiro.jokenpokemon.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object PontuacaoService {

     fun getJokenPokemonAPI(): JokenPokemonAPI {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://gamestjd.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit
                .create<JokenPokemonAPI>(JokenPokemonAPI::class.java)
    }
}