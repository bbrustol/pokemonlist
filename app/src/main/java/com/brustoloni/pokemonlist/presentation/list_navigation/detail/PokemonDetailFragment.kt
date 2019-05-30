package com.brustoloni.pokemonlist.presentation.list_navigation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.brustoloni.pokemonlist.R
import com.brustoloni.pokemonlist.databinding.FragmentPokemonDetailBinding
import com.brustoloni.pokemonlist.presentation.webview.webviewActivity
import com.brustoloni.pokemonlist.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailFragment : Fragment() {

    private val viewModel: PokemonDetailViewModel by viewModel()

    private lateinit var dataBinding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_pokemon_detail, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
        }
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
        dataBinding.executePendingBindings()

        var name = ""
        arguments?.let { bundle ->
             name = bundle.getString(Constants.EXTRA_NAME_POKEMON) ?: ""
        }

        viewModel.start(name)
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {

        val clickAction = { url: String ->
            startActivity(webviewActivity().getLaunchingIntent(context, url))
        }

        val adapter = PokemonDetailAdapter(clickAction)
        dataBinding.rvPokemonDetail.adapter = adapter
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) { 2 } else { 1 }
            }
        }

        dataBinding.rvPokemonDetail.layoutManager = gridLayoutManager


        viewModel.dataReceived.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
        })
    }
}
