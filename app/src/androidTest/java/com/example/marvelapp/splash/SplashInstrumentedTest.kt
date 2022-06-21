package com.example.marvelapp.splash

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.marvelapp.MainActivity
import com.example.marvelapp.R
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

    private lateinit var decorView: View

    @Before
    fun prepare() {
        activityRule.scenario.onActivity {
            decorView = it.window.decorView
        }
    }

    @Test
    fun splashFragmentIsVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.cl_splash)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateFromSplashToCharacterListAfterThreeSeconds() {
        Espresso.onView(ViewMatchers.withId(R.id.cl_splash)).check(matches(isDisplayed()))
        Thread.sleep(3_000)
        Espresso.onView(ViewMatchers.withId(R.id.cl_character_list)).check(matches(isDisplayed()))
    }

}