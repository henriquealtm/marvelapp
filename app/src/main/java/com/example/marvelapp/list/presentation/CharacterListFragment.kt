package com.example.marvelapp.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelapp.databinding.FragmentCharacterListBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val characterListVm: CharacterListViewModel by viewModel()

    private val characterAdapter = CharacterListAdapter { character ->
        Snackbar.make(binding.root, character.name, Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharacterListBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        vm = characterListVm
        binding = this
    }.root

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
        lifecycle.addObserver(characterListVm)
    }

    private fun setupObservers() {
        characterListVm.list.observe(viewLifecycleOwner) { list ->
            characterAdapter.submitList(list)
        }
    }

}