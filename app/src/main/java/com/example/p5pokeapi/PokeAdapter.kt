package com.example.p5pokeapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PetAdapter(private val petList: List<Pokemon>) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokeImage: ImageView = view.findViewById(R.id.poke_image)
        val pokeName: TextView = view.findViewById(R.id.poke_name)
        val pokeAbility: TextView = view.findViewById(R.id.poke_ability)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPokemon = petList[position]
        Glide.with(holder.itemView)
            .load(currentPokemon.imageUrl)
            .centerCrop()
            .into(holder.pokeImage)
        holder.pokeName.text = currentPokemon.name
        holder.pokeAbility.text = currentPokemon.ability
    }
    override fun getItemCount() = petList.size
}