package com.brustoloni.pokemonlist.presentation.list_navigation.pokemonList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.brustoloni.pokemonlist.R
import com.brustoloni.pokemonlist.data.entity.pokemon.list.Result
import com.brustoloni.pokemonlist.databinding.FragmentPokemonListBinding
import com.brustoloni.pokemonlist.utils.Constants
import com.brustoloni.pokemonlist.utils.loadMore
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonListFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModel()

    private lateinit var dataBinding: FragmentPokemonListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_pokemon_list, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
        }
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
        dataBinding.executePendingBindings()

        initializeRecyclerView(view)
        setupListeners()

        if (!viewModel.flagFirstLoad.value!!) {
            viewModel.flagFirstLoad.value = true
            viewModel.start()
        }
    }

    private fun initializeRecyclerView(view: View) {

        dataBinding.rvPokemonList.layoutManager = GridLayoutManager(context, 2)

        val clickAction = { result: Result ->

            val bundle = Bundle().apply { putString(Constants.EXTRA_NAME_POKEMON, result.name) }

            view.findNavController().navigate(R.id.actionPokemonListToDetail, bundle)
        }

        dataBinding.rvPokemonList.adapter = PokemonListAdapter(clickAction)
    }

    private fun setupListeners() {

        viewModel.dataReceived.observe(viewLifecycleOwner, Observer {
            val pokemonListAdapter = rv_pokemon_list.adapter as PokemonListAdapter
            pokemonListAdapter.submitList(it)
        })

        rv_pokemon_list.loadMore { viewModel.start() }
    }
}
