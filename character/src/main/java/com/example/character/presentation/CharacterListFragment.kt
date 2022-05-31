package com.example.character.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.character.databinding.FragmentCharacterListBinding
import com.example.character.di.CharacterModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val characterListVm: CharacterListViewModel by viewModel()

    private val characterAdapter = CharacterListAdapter { character ->
        Toast.makeText(
            requireContext(),
            "Clicked - ${character.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(CharacterModule.module)
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
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.rvCharacter.adapter = characterAdapter
    }

    private fun setupObservers() {
        characterListVm.list.observe(viewLifecycleOwner) { list ->
            characterAdapter.submitList(list)
        }
    }

}