package com.example.pokedex

import android.graphics.Color
import java.io.Serializable
/**
 *  Cette classe regroupe les données que l'on récupère depuis l'API
 */
class Pokemon(
    //var id:String? ,
    var ndex:String? ,
    var forme:String? ,
    var type1:String? ,
    var type2:String? ,
    var ability1:String? ,
    var ability2:String? ,
    //var abilityH:String? ,
    var hp:String? ,
    var attack:String? ,
    var defense:String? ,
    var spattack:String? ,
    var spdefense:String? ,
    var speed:String? ,
    //var total:String? ,
    var weight:String? ,
    var height:String? ,
    //var percentmale:String? ,
    //var percentfemale:String? ,
    //var preevolution:String? ,
    //var egggroup1:String? ,
    //var egggroup2:String?
): Serializable {
    
    fun getBackgroundColor(): Int = when(type1){
        "Grass" -> Color.GREEN
        "Fire" -> Color.RED
        "Water" -> Color.BLUE
        "Bug" -> Color.parseColor("#008000")
        "Electric" -> Color.YELLOW
        "Rock" -> Color.GRAY
        "Ground" -> Color.DKGRAY
        "Fighting" -> Color.parseColor("#800000")
        "Psychic" -> Color.parseColor("#800080")
        "Dark" -> Color.parseColor("#000000")
        "Fairy" -> Color.parseColor("#FF00FF")
        else -> Color.WHITE
    }
}