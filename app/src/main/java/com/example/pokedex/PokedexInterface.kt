package com.example.pokedex

import retrofit2.Call
import retrofit2.http.GET

/**
 * L'interface de récupération des données sur l'API
 */
interface PokedexInterface {

    @get:GET("/pokemons?from=theo")
    val posts : Call<List<Pokemon?>?>?

    companion object {
        const val URL = "https://api.vilteros.ovh/"
    }
}