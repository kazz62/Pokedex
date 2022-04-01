package com.example.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class PokemonData : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_data)
        // Récupération des données transmises de l'autre activité
        val extras = intent.extras
        val pokemon = extras?.getSerializable("pokemon") as? Pokemon

        pokemon?.let {
            // Mise à jour de l'imageview avec l'image du pokémon
            val image = findViewById<ImageView>(R.id.imageView)
            val urlImg = "https://api.vilteros.ovh/public/images/pokemons/" + pokemon.ndex + ".png"
            Picasso.get().load(urlImg).into(image)

            findViewById<TextView>(R.id.textView).setText(pokemon.forme)
            findViewById<TextView>(R.id.textView2).setText(pokemon.type1)
            findViewById<TextView>(R.id.textView3).setText(pokemon.ability1)
            findViewById<TextView>(R.id.textView6).setText("#" + pokemon.ndex)
            findViewById<TextView>(R.id.textView7).setText("Attack: " + pokemon.attack)
            findViewById<TextView>(R.id.textView8).setText("Defense: " + pokemon.defense)
            findViewById<TextView>(R.id.textView9).setText("Spe Attack: " + pokemon.spattack)
            findViewById<TextView>(R.id.textView10).setText("Hp: " + pokemon.hp)
            findViewById<TextView>(R.id.textView11).setText("Spe Defense: " + pokemon.spdefense)
            findViewById<TextView>(R.id.textView12).setText("Speed: " + pokemon.speed)
            findViewById<TextView>(R.id.textView13).setText("Weight: " + pokemon.weight)
            findViewById<TextView>(R.id.textView14).setText("Height: " + pokemon.height)

            if(pokemon.type2 == "null" || pokemon.type2 == pokemon.type1){
                findViewById<TextView>(R.id.textView5).visibility = View.INVISIBLE
            }else {
                findViewById<TextView>(R.id.textView5).setText(pokemon.type2)
            }
            if(pokemon.ability2 == "null"|| pokemon.ability2 == pokemon.ability1){
                findViewById<TextView>(R.id.textView4).visibility = View.INVISIBLE
            }else {
                findViewById<TextView>(R.id.textView4).setText(pokemon.ability2)
            }
            image.setBackgroundColor(pokemon.getBackgroundColor())
        }
    }
}