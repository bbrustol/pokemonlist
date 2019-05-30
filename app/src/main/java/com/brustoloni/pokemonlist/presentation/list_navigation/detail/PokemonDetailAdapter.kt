package com.brustoloni.pokemonlist.presentation.list_navigation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.brustoloni.pokemonlist.R
import com.brustoloni.pokemonlist.data.entity.pokemon.detail.DetailFormatted
import com.brustoloni.pokemonlist.databinding.ItemCardPokemonDetailBinding
import com.brustoloni.pokemonlist.databinding.ItemCardPokemonDetailHeaderBinding


class PokemonDetailAdapter(val onItemClickAction: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var myContext: Context
    private var updatesList: List<DetailFormatted> = listOf()
    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_VALUE = 1
    }

    override fun getItemCount(): Int = updatesList.size

    override fun getItemViewType(position: Int): Int {
        return if (updatesList[position].type == "header") {
            TYPE_HEADER
        } else {
            TYPE_VALUE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        myContext = parent.context

        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_HEADER -> {
                HeaderViewHolder(inflate(
                    layoutInflater,
                    R.layout.item_card_pokemon_detail_header, parent, false
                ))
            }
            TYPE_VALUE -> {
                ValueViewHolder(inflate(
                    layoutInflater,
                    R.layout.item_card_pokemon_detail, parent, false
                ))
            } else -> throw IllegalArgumentException()

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val element = updatesList[position]
        when (holder) {
            is HeaderViewHolder -> holder.bind(element)
            is ValueViewHolder -> holder.bind(element)
            else -> throw IllegalArgumentException()
        }
    }

    fun update(update: List<DetailFormatted>) {
        updatesList = update
    }

    inner class HeaderViewHolder(private val binding: ItemCardPokemonDetailHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(response: DetailFormatted) = with(binding) {
            item = response
//            root.setOnClickListener { response.stat_url -> onItemClickAction(it1)  }
            executePendingBindings()
        }
    }

    inner class ValueViewHolder(private val binding: ItemCardPokemonDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(response: DetailFormatted) = with(binding) {
            item = response
//            root.setOnClickListener { response.stat?.url?.let { it1 -> onItemClickAction(it1) } }
            executePendingBindings()
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}