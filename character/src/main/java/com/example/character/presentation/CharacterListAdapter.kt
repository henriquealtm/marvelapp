package com.example.character.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.character.R
import com.example.character.databinding.ItemCharacterBinding
import com.example.character.domain.Character

internal class CharacterAdapter(
    private val onItemClick: (Character) -> Unit,
) : ListAdapter<Character, CharacterAdapter.DataHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_character,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.binding.character = getItem(position)
    }

    inner class DataHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private object DiffCallback : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(
            oldItem: Character,
            newItem: Character
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character
        ) = oldItem == newItem
    }

}