package com.example.marvelapp.list

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.marvelapp.MainActivity
import com.example.marvelapp.R
import com.example.marvelapp.commons.RecyclerViewItemCountAssertion
import com.example.marvelapp.commons.RecyclerViewMatcher
import com.example.marvelapp.list.data.CharacterServiceErrorMock
import com.example.marvelapp.list.data.CharacterServiceLoadingMock
import com.example.marvelapp.list.data.CharacterServiceSuccessMock
import com.example.marvelapp.list.data.InstrumentedTestCharacterModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class CharacterListInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val dataList = CharacterServiceSuccessMock().list
    private lateinit var decorView: View

    @Before
    fun prepare() {
        activityRule.scenario.onActivity {
            decorView = it.window.decorView
        }
    }

    // Resource Success
    private fun loadSuccessMock() {
        loadKoinModules(InstrumentedTestCharacterModule.module(CharacterServiceSuccessMock()))
        // The time spent in the Splash Screen
        Thread.sleep(3_500)
    }

    @Test
    fun characterListVerifyListSize() {
        loadSuccessMock()
        onView(withId(R.id.rv_character)).check(RecyclerViewItemCountAssertion(3))
    }

    @Test
    fun characterListVerifyContentOnItemView() {
        loadSuccessMock()
        dataList.forEachIndexed { index, characterDto ->
            onView(
                RecyclerViewMatcher(R.id.rv_character).atPositionOnView(
                    index,
                    R.id.tv_character_name
                )
            ).check(matches(withText(characterDto.name)))
            onView(
                RecyclerViewMatcher(R.id.rv_character).atPositionOnView(
                    index,
                    R.id.tv_character_description
                )
            ).check(matches(withText(characterDto.description)))
        }
    }

    @Test
    fun characterListVerifyClickItemAndToastText() {
        loadSuccessMock()
        dataList.forEachIndexed { index, characterDto ->
            onView(
                RecyclerViewMatcher(R.id.rv_character).atPositionOnView(
                    index,
                    R.id.tv_character_name
                )
            ).perform(click())
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(characterDto.name)))
        }
    }

    @Test
    fun characterListVerifyFilter() {
        loadSuccessMock()

        onView(withId(R.id.et_search)).perform(typeText("3"))
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        Thread.sleep(2_000)
        onView(withId(R.id.rv_character)).check(matches(isDisplayed()))

        onView(withId(R.id.et_search)).perform(typeText("D-Man"))
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        Thread.sleep(2_000)
        onView(withId(R.id.rv_character)).check(matches(isDisplayed()))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        Thread.sleep(2_000)
        onView(withId(R.id.rv_character)).check(matches(isDisplayed()))
    }

    @Test
    fun scrollToBottomShowListAndLoader() {
        loadSuccessMock()
    }

    // Resource Loading (delay)
    private fun loadLoadingMock() {
        loadKoinModules(InstrumentedTestCharacterModule.module(CharacterServiceLoadingMock()))
        // The time spent in the Splash Screen
        Thread.sleep(3_500)
    }

    @Test
    fun characterListProgressBarVisible() {
        loadLoadingMock()
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    // Resource Loading (delay)
    private fun loadErrorMock() {
        loadKoinModules(InstrumentedTestCharacterModule.module(CharacterServiceErrorMock()))
        // The time spent in the Splash Screen
        Thread.sleep(3_500)
    }

    @Test
    fun characterListErrorContainerVisible() {
        loadErrorMock()
        onView(withId(R.id.ll_error_container)).check(matches(isDisplayed()))
    }

}
