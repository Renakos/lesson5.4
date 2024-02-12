package com.example.lesson54.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson54.R
import com.example.lesson54.data.model.ResultsItem

class CharacterAdapter :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characters = listOf<ResultsItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun updateData(newData: List<ResultsItem>) {
        characters = newData
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterNameTextView: TextView =
            itemView.findViewById(R.id.characterNameTextView)
        private val characterSpeciesTextView: TextView =
            itemView.findViewById(R.id.characterSpeciesTextView)

        fun bind(resultsItem: ResultsItem) {
            characterNameTextView.text = resultsItem.name
            characterSpeciesTextView.text = resultsItem.species
        }
    }
}

