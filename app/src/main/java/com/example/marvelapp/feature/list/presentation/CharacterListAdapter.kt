package com.example.marvelapp.feature.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemCharacterBinding
import com.example.marvelapp.feature.list.domain.model.Character

internal class CharacterListAdapter(
    private val onItemClick: (Character) -> Unit,
) : ListAdapter<Character, CharacterListAdapter.DataHolder>(DiffCallback) {

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
        holder.binding.run {
            val item = getItem(position)
            character = item
            clContainer.setOnClickListener { onItemClick(item) }
        }
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