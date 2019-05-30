package com.brustoloni.pokemonlist.presentation.list_navigation.pokemonList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brustoloni.pokemonlist.R
import com.brustoloni.pokemonlist.data.entity.pokemon.list.Result
import com.brustoloni.pokemonlist.databinding.ItemCardPokemonListBinding


class PokemonListAdapter(val onItemClickAction: (Result) -> Unit) : ListAdapter<Result, PokemonListAdapter.PokemonListViewHandler>(
    PokemonListDiffCallback()) {

    private lateinit var myContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHandler {

        myContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemBinding = inflate<ItemCardPokemonListBinding>(
            layoutInflater,
            R.layout.item_card_pokemon_list, parent, false
        )

        return PokemonListViewHandler(itemBinding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHandler, position: Int) = holder.bind(getItem(position))

    inner class PokemonListViewHandler(private val binding: ItemCardPokemonListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) = with(binding) {
            item = result
            root.setOnClickListener { onItemClickAction(result) }
            executePendingBindings()
        }
    }

    class PokemonListDiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}