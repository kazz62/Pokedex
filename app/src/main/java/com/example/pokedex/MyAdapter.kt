package com.example.pokedex

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(private var mListener : (Pokemon) -> Unit) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val pokemonList: MutableList<Pokemon> = mutableListOf()

    fun refresh(pokemons : List<Pokemon>){
        pokemonList.addAll(pokemons.toMutableList())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pokedex_item,
        parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentPokemon = pokemonList[position]
        holder.bind(currentPokemon)

    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class MyViewHolder(itemView : View, var listener: (Pokemon) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
        private val nom : TextView = itemView.findViewById(R.id.pokemon_name)

        fun bind(pokemon: Pokemon){
            val urlImg = "https://api.vilteros.ovh/public/images/pokemons/" + pokemon.ndex + ".png"
            Picasso.get().load(urlImg).into(titleImage) //Utiliser glide
            nom.text = "${pokemon.forme} #${pokemon.ndex}"

            itemView.setOnClickListener {
                listener(pokemon)
            }
        }
    }
}

