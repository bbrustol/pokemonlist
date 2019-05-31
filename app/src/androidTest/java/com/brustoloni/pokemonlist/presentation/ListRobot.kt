package com.brustoloni.pokemonlist.presentation

import com.brustoloni.pokemonlist.R
import com.brustoloni.pokemonlist.presentation.setup.BaseRobot

class ListRobot : BaseRobot() {

    fun withEmptyImage(): ListRobot {
        matchDrawable(R.id.imageStatus, R.drawable.empty)
        return this
    }
    fun withErrorImage(): ListRobot {
        matchDrawable(R.id.imageStatus, R.drawable.sad_cloud)
        return this
    }

    fun withEmptyText(): ListRobot {
        matchText(R.string.empty_msg_try_again)
        return this
    }

    fun withErrorText(): ListRobot {
        matchText(R.string.error_msg_try_again)
        return this
    }

    fun clickInTryAgain(): ListRobot {
        click(R.id.tryAgainButton)
        return this
    }

    fun waitAlternativeView(): ListRobot {
        Thread.sleep(1000)
        matchId(R.id.imageStatus)
        return this
    }
}