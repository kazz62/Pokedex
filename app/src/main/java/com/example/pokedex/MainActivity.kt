package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// https://medium.com/android-beginners/mvvm-with-kotlin-coroutines-and-retrofit-example-d3f5f3b09050
// medium tuto
// clap clap

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.setHasFixedSize(true)
        val adapter = MyAdapter { pokemon ->
            val intent = Intent(applicationContext, PokemonData::class.java)
            intent.putExtra("pokemon", pokemon)
            startActivity(intent)
        }
        newRecyclerView.adapter = adapter

        // variable utilisant retrofit pour récupérer les données de l'api et les convertir en données JSON
        val rf = Retrofit.Builder()
            .baseUrl(PokedexInterface.URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = rf.create(PokedexInterface::class.java)
        val call = api.posts //Récupération de la liste de données

        call?.enqueue(object:Callback<List<Pokemon?>?>{ //Coroutine retrofit example
            override fun onResponse(
                call: Call<List<Pokemon?>?>,
                response: Response<List<Pokemon?>?>
            ) {
                val postlist : List<Pokemon>? = response.body() as List<Pokemon>? //liste qui stock le retour du Call

                if(postlist.isNullOrEmpty()){
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }else {
                    adapter.refresh(postlist)
                }
            }
            override fun onFailure(call: Call<List<Pokemon?>?>, t: Throwable) {
            }
        })
    }
}