package com.brustoloni.pokemonlist.presentation

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.brustoloni.pokemonlist.presentation.list_navigation.MainActivity
import com.brustoloni.pokemonlist.presentation.setup.BaseInstrumentedTest
import com.brustoloni.pokemonlist.presentation.setup.Constants.Companion.EMPTY_LIST
import com.brustoloni.pokemonlist.presentation.setup.Constants.Companion.NOT_FOUND_ERROR_CODE
import com.brustoloni.pokemonlist.presentation.setup.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PoiListTest : BaseInstrumentedTest() {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    private val listRobot = ListRobot()

    @Test
    fun shouldShowEmptyView() {

        setupMockWebServer(EMPTY_LIST)

        activityRule.launchActivity(Intent())

        verify {
            listRobot
                .waitAlternativeView()
                .withEmptyImage()
                .withEmptyText()
                .clickInTryAgain()
        }
    }

    @Test
    fun shouldShowErrorView() {

        setupMockWebServer(EMPTY_LIST, statusCode = NOT_FOUND_ERROR_CODE)

        activityRule.launchActivity(Intent())

        verify {
            listRobot
                .waitAlternativeView()
                .withErrorImage()
                .withErrorText()
                .clickInTryAgain()
        }
    }
}