package com.brustoloni.pokemonlist.presentation.list_navigation.pokemonList

import android.app.Application
import android.view.View.GONE
import androidx.lifecycle.MutableLiveData
import com.brustoloni.pokemonlist.business.PokemonBusiness
import com.brustoloni.pokemonlist.data.entity.pokemon.list.Result
import com.brustoloni.pokemonlist.data.entity.pokemon.response.PokemonListResponse
import com.brustoloni.pokemonlist.data.infraestructure.Failure
import com.brustoloni.pokemonlist.data.infraestructure.Success
import com.brustoloni.pokemonlist.data.infraestructure.handle
import com.brustoloni.pokemonlist.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val pokemonBusiness: PokemonBusiness,
    application: Application,
    private val coroutineScope: CoroutineScope
) : BaseViewModel(application) {

    val dataReceived: MutableLiveData<List<Result>> = MutableLiveData()
    val listVisibility = MutableLiveData<Int>().apply { value = GONE }
    val flagFirstLoad = MutableLiveData<Boolean>().apply { value = false }

    private var tempData: ArrayList<Result> = arrayListOf()
    private var offset = 0
    private var limit = 50

    fun start() {
        configVisibility(ViewState.LOADING)
        coroutineScope.launch {
            val resource = pokemonBusiness.fetchPokemonlist(offset*limit, limit)
            resource.handle(success(), failure())
        }
    }

    private fun failure(): Failure.() -> Unit = {
        configVisibility(ViewState.ERROR)
    }

    private fun success(): Success<PokemonListResponse>.() -> Unit = {
        if (!this.data.results.isNullOrEmpty()) {
            offset++
            var count = 0
            if (!dataReceived.value.isNullOrEmpty()) {
                tempData = ArrayList(dataReceived.value!!)
                count = tempData.size - 1
            }

            for (index in this.data.results.indices) {
                tempData.add(this.data.results[index])
            }

            for (index in this.data.results.indices) {
                val url = tempData[count + index].url.dropLast(1)
                val urlId = url.length.let { url.substring(url.lastIndexOf("/")+1, it) }
                tempData[count + index].strThumb = "$imageUrl$urlId$imageExtension"
            }

            dataReceived.value = tempData.toList()

            configVisibility(ViewState.SUCCESS)
        } else if (offset == 0) {
            configVisibility(ViewState.NO_DATA)
        } else {
            configVisibility(ViewState.STOP_LOADING)
        }
    }

    override fun configVisibility(viewState: ViewState) {
        super.configVisibility(viewState)
        val result = setupViewState(viewState)
        listVisibility.value = result.showData
    }

    override fun tryAgain() {
        start()
    }

    companion object {
        const val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        const val imageExtension = ".png"
    }
}