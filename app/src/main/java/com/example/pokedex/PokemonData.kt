package com.example.pokedex

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class PokemonData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_data)
        // Récupération des données transmises de l'autre activité
        val extras = intent.extras
        var index = ""
        var name = ""
        var type1 = ""
        var type2 = ""
        var abi1 = ""
        var abi2 = ""
        var hp = ""
        var atck = ""
        var def = ""
        var speatck = ""
        var spedef = ""
        var speed = ""
        var weight = ""
        var height = ""
        var preev = ""

        if (extras != null) {
            index = extras.getString("index").toString()
            name = extras.getString("name").toString()
            type1 = extras.getString("type1").toString()
            abi1 = extras.getString("ability1").toString()
            abi2 = extras.getString("ability2").toString()
            type2 = extras.getString("type2").toString()
            hp = extras.getString("hp").toString()
            atck = extras.getString("atck").toString()
            def = extras.getString("def").toString()
            speatck = extras.getString("speatck").toString()
            spedef = extras.getString("spedefense").toString()
            speed = extras.getString("speed").toString()
            weight = extras.getString("weight").toString()
            height = extras.getString("height").toString()
            preev = extras.getString("preev").toString()
        }

        // Mise à jour de l'imageview avec l'image du pokémon
        val image = findViewById<ImageView>(R.id.imageView)
        val urlImg = "https://api.vilteros.ovh/public/images/pokemons/" + index + ".png"
        Picasso.get().load(urlImg).into(image)

        findViewById<TextView>(R.id.textView).setText(name)
        findViewById<TextView>(R.id.textView2).setText(type1)
        findViewById<TextView>(R.id.textView3).setText(abi1)
        if(type2 == "null" || type2 == type1){
            findViewById<TextView>(R.id.textView5).visibility = View.INVISIBLE
        }else {
            findViewById<TextView>(R.id.textView5).setText(type2)
        }
        findViewById<TextView>(R.id.textView6).setText("#" + index)
        findViewById<TextView>(R.id.textView7).setText("Attack: " + atck)
        findViewById<TextView>(R.id.textView8).setText("Defense: " + def)
        findViewById<TextView>(R.id.textView9).setText("Spe Attack: " + speatck)
        findViewById<TextView>(R.id.textView10).setText("Hp: " + hp)
        findViewById<TextView>(R.id.textView11).setText("Spe Defense: " + spedef)
        findViewById<TextView>(R.id.textView12).setText("Speed: " + speed)
        findViewById<TextView>(R.id.textView13).setText("Weight: " + weight)
        findViewById<TextView>(R.id.textView14).setText("Height: " + height)

        if(abi2 == "null"|| abi2 == abi1){
            findViewById<TextView>(R.id.textView4).visibility = View.INVISIBLE
        }else {
            findViewById<TextView>(R.id.textView4).setText(abi2)
        }

        //couleur de fond en fonction du type du pokémon
        when(type1){
            "Grass" -> image.setBackgroundColor(Color.GREEN)
            "Fire" -> image.setBackgroundColor(Color.RED)
            "Water" -> image.setBackgroundColor(Color.BLUE)
            "Bug" -> image.setBackgroundColor(Color.parseColor("#008000"))
            "Electric" -> image.setBackgroundColor(Color.YELLOW)
            "Rock" -> image.setBackgroundColor(Color.GRAY)
            "Ground" -> image.setBackgroundColor(Color.DKGRAY)
            "Fighting" -> image.setBackgroundColor(Color.parseColor("#800000"))
            "Psychic" -> image.setBackgroundColor(Color.parseColor("#800080"))
            "Dark" -> image.setBackgroundColor(Color.parseColor("#000000"))
            "Fairy" -> image.setBackgroundColor(Color.parseColor("#FF00FF"))
            else -> image.setBackgroundColor(Color.WHITE)
        }

    }
}