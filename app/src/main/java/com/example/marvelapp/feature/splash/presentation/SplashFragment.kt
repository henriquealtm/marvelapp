package com.example.marvelapp.feature.splash.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.commons.presentation.BaseFragment
import com.example.marvelapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashFragment : BaseFragment(
    R.layout.fragment_splash,
    "SplashScreenFragment",
) {

    private val splashVm: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNavigateToCharacterListScreen()
    }

    private fun observeNavigateToCharacterListScreen() {
        splashVm.navigateToListScreen.observe(viewLifecycleOwner) { mustNavigate ->
            if (mustNavigate) {
                findNavController().navigate(
                    SplashFragmentDirections.goToCharacterListScreen()
                )
            }
        }
    }

}