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

        // variable utilisant retrofit pour récupérer les données de l'API et les convertir en données JSON
        var rf = Retrofit.Builder()
            .baseUrl(PokedexInterface.URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(PokedexInterface::class.java)
        var call = API.posts //Récupération de la liste de données

        call?.enqueue(object:Callback<List<Pokemon?>?>{
            override fun onResponse(
                call: Call<List<Pokemon?>?>,
                response: Response<List<Pokemon?>?>
            ) {
                var postlist : List<Pokemon>? = response.body() as List<Pokemon>? //liste qui stock le retour du Call
                var post = arrayOfNulls<String>(postlist!!.size)

                for(i in postlist.indices) // ajout des datas choisies dans l'adapter
                    post[i] = postlist[i].forme + " #" + postlist[i].ndex

                "${postlist.first().forme} "

                var adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_dropdown_item_1line, post)
                val listview = findViewById<ListView>(R.id.listview)
                listview.adapter = adapter //recyclerView

                /**
                 * Au click sur un élément de la liste,
                 * on envoie les données de la case choisie
                 * à la prochain activité
                 */
                listview.setOnItemClickListener { parent, view, position, id ->
                    //val listItem: Any = listview.getItemAtPosition(position)
                    val intent = Intent(applicationContext, PokemonData::class.java)

                    // Ajout des informations sur le pokémon
                    intent.putExtra("name",postlist[position].forme)
                    intent.putExtra("index",postlist[position].ndex)
                    intent.putExtra("type1",postlist[position].type1)
                    intent.putExtra("type2",postlist[position].type2)
                    intent.putExtra("ability1",postlist[position].ability1)
                    intent.putExtra("ability2",postlist[position].ability2)

                    intent.putExtra("hp",postlist[position].hp)
                    intent.putExtra("atck",postlist[position].attack)
                    intent.putExtra("def",postlist[position].defense)
                    intent.putExtra("speatck",postlist[position].spattack)
                    intent.putExtra("spedefense",postlist[position].spdefense)
                    intent.putExtra("speed",postlist[position].speed)

                    intent.putExtra("weight",postlist[position].weight)
                    intent.putExtra("height",postlist[position].height)
                    intent.putExtra("preevolution",postlist[position].preevolution)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Pokemon?>?>, t: Throwable) {
            }
        });
    }
}