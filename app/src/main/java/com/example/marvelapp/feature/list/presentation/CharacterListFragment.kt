package com.example.marvelapp.feature.list.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.commons.presentation.BaseBindingFragment
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterListBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : BaseBindingFragment<FragmentCharacterListBinding>(
    R.layout.fragment_character_list,
    "CharacterListFragment",
) {

    private val characterListVm: CharacterListViewModel by viewModel()

    private val characterAdapter = CharacterListAdapter { character ->
        Snackbar.make(binding.root, character.name, Snackbar.LENGTH_LONG).show()
        analyticsLog.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            bundleOf(
                FirebaseAnalytics.Param.ITEM_ID to character.id,
                FirebaseAnalytics.Param.ITEM_NAME to character.name
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModel()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.rvCharacter.adapter = characterAdapter
    }

    private fun setupViewModel() {
        binding.vm = characterListVm
        lifecycle.addObserver(characterListVm)
    }

    private fun setupObservers() {
        observeBackNavigation()
        observeList()
    }

    private fun observeBackNavigation() {
        characterListVm.navigateBack.observe(viewLifecycleOwner) { mustNavigateBack ->
            if (mustNavigateBack) {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun observeList() {
        characterListVm.list.observe(viewLifecycleOwner) { list ->
            characterAdapter.submitList(list)
        }
    }

}