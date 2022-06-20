package com.example.marvelapp.list

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.marvelapp.MainActivity
import com.example.marvelapp.R
import com.example.marvelapp.commons.RecyclerViewItemCountAssertion
import com.example.marvelapp.commons.RecyclerViewMatcher
import com.example.marvelapp.list.data.CharacterServiceMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class CharacterListInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val dataList = CharacterServiceMock().list
    private lateinit var decorView: View

    @Before
    fun prepare() {
        activityRule.scenario.onActivity {
            decorView = it.window.decorView
        }

        // The time spent in the Splash Screen
        Thread.sleep(3_000)
    }

    @Test
    fun characterListVerifyListSize() {
        onView(withId(R.id.rv_character)).check(RecyclerViewItemCountAssertion(3))
    }

    @Test
    fun characterListVerifyContentOnItemView() {
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
        onView(withId(R.id.et_search)).perform(typeText("3D-Man"))
        onView(withId(R.id.rv_character)).check(RecyclerViewItemCountAssertion(1))
        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.rv_character)).check(RecyclerViewItemCountAssertion(3))
        onView(withId(R.id.et_search)).perform(typeText("Bomb"))
        onView(withId(R.id.rv_character)).check(RecyclerViewItemCountAssertion(1))
        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.rv_character)).check(RecyclerViewItemCountAssertion(3))
        onView(withId(R.id.et_search)).perform(typeText("9"))
        onView(withId(R.id.rv_character)).check(RecyclerViewItemCountAssertion(0))
    }
    
}
