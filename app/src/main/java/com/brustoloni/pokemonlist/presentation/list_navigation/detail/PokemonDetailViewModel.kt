package com.brustoloni.pokemonlist.presentation.list_navigation.detail

import android.app.Application
import android.view.View.GONE
import androidx.lifecycle.MutableLiveData
import com.brustoloni.pokemonlist.business.PokemonBusiness
import com.brustoloni.pokemonlist.data.entity.pokemon.detail.DetailFormatted
import com.brustoloni.pokemonlist.data.entity.pokemon.response.PokemonDetailResponse
import com.brustoloni.pokemonlist.data.infraestructure.Failure
import com.brustoloni.pokemonlist.data.infraestructure.Success
import com.brustoloni.pokemonlist.data.infraestructure.handle
import com.brustoloni.pokemonlist.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val pokemonBusiness: PokemonBusiness,
    application: Application,
    private val coroutineScope: CoroutineScope
) : BaseViewModel(application) {

    val dataReceived: MutableLiveData<List<DetailFormatted>> = MutableLiveData()
    val screenVisibility = MutableLiveData<Int>().apply { value = GONE }
    private var mName = ""
    fun start(name: String) {
        mName = name
        configVisibility(ViewState.LOADING)
        coroutineScope.launch {
            val resource = pokemonBusiness.fetchPokemonDetail(name)
            resource.handle(success(), failure())
        }
    }

    private fun failure(): Failure.() -> Unit = {
        configVisibility(ViewState.ERROR)
    }

    private fun success(): Success<PokemonDetailResponse>.() -> Unit = {
        if (!this.data.stats.isNullOrEmpty()) {
            dataReceived.value = formatResponse(this.data)
            configVisibility(ViewState.SUCCESS)
        } else {
            configVisibility(ViewState.NO_DATA)
        }
    }

    private fun formatResponse(data: PokemonDetailResponse): ArrayList<DetailFormatted> {

        val detailFormatted: ArrayList<DetailFormatted> = arrayListOf()

        detailFormatted.add(
            DetailFormatted(
                data.name ?: "",
                data.sprites?.front_default ?: "",
                "",
                "",
                "",
                "header"
            )
        )

        for (stat in data.stats!!) {
            detailFormatted.add(
                DetailFormatted(
                    "",
                    "",
                    stat?.base_stat.toString(),
                    stat?.stat?.name ?: "",
                    stat?.stat?.url ?: "",
                    "value"
                )
            )
        }
        return detailFormatted
    }

    override fun configVisibility(viewState: ViewState) {
        super.configVisibility(viewState)
        val result = setupViewState(viewState)
        screenVisibility.value = result.showData
    }

    override fun tryAgain() {
        start(mName)
    }
}