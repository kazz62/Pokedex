package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // variable utilisant retrofit pour récupérer les données de l'api et les convertir en données JSON
        val rf = Retrofit.Builder()
            .baseUrl(PokedexInterface.URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = rf.create(PokedexInterface::class.java)
        val call = api.posts //Récupération de la liste de données

        call?.enqueue(object:Callback<List<Pokemon?>?>{
            override fun onResponse(
                call: Call<List<Pokemon?>?>,
                response: Response<List<Pokemon?>?>
            ) {
                val postlist : List<Pokemon>? = response.body() as List<Pokemon>? //liste qui stock le retour du Call
                val post = arrayOfNulls<String>(postlist!!.size)

                for(i in postlist.indices) // ajout des datas choisies dans l'adapter
                    //post[i] = postlist[i].forme + " #" + postlist[i].ndex
                    post[i] = "${postlist[i].forme} #${postlist[i].ndex}"

                val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_dropdown_item_1line, post)
                val listview = findViewById<ListView>(R.id.listview)
                listview.adapter = adapter //recyclerView

                /**
                 * Au click sur un élément de la liste,
                 * on envoie les données de la case choisie
                 * à la prochain activité
                 */
                listview.setOnItemClickListener { _, _, position, _ ->
                    //val listItem: Any = listview.getItemAtPosition(position)
                    val intent = Intent(applicationContext, PokemonData::class.java)
                    intent.putExtra("pokemon",postlist[position])
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Pokemon?>?>, t: Throwable) {
            }
        })
    }
}