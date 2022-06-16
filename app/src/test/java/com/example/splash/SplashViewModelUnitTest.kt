package com.example.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.commons.extension.handleOpt
import com.example.marvelapp.feature.splash.presentation.SplashViewModel
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SplashViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var splashVm: SplashViewModel
    private val navigateToListScreenObserver = Observer<Boolean> {}

    @Before
    fun prepare() {
        Dispatchers.setMain(testDispatcher)
        splashVm = SplashViewModel()
        splashVm.navigateToListScreen.observeForever(navigateToListScreenObserver)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        splashVm.navigateToListScreen.removeObserver(navigateToListScreenObserver)
    }

    @Test
    fun `GIVEN the initial state of SplashViewModel THEN navigateToListScreen_value is false`() {
        assertFalse(splashVm.navigateToListScreen.value.handleOpt())
    }

    @Test
    fun `GIVEN that passed 3 seconds after creating the SplashViewModel THEN navigateToListScreen_value is true`() = runTest {
        launch {
            delay(3_000L)
            assertTrue(splashVm.navigateToListScreen.value.handleOpt())
        }
    }

}