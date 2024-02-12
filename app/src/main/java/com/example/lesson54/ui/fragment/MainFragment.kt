package com.example.lesson54.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.lesson54.data.viewmodel.MainViewModel
import com.example.lesson54.databinding.FragmentMainBinding
import com.example.lesson54.ui.adapter.CharacterAdapter

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var characterAdapter = CharacterAdapter()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacters()
        characterAdapter.updateData(viewModel.charactersList)
        binding.errorTextView.text = null
        setupRecyclerView()
        viewModel.uistate.observe(viewLifecycleOwner) { uiState ->
            if (!uiState.isLoading) {
                binding.progressBar.isInvisible = true
                binding.recyclerView.isVisible = true
                if (uiState.success!!.isNotEmpty()) {
                    Log.e("uiState", "onViewCreated: successful ${uiState.success}")
                    characterAdapter.updateData(uiState.success)
                    binding.errorTextView.visibility = View.GONE
                } else {
                    Log.e("uiState", "onViewCreated: error ${uiState.errorMessage}")
                    binding.errorTextView.text = uiState.errorMessage
                }
            } else {
                Log.e("uiState", "onViewCreated: loading")
                binding.progressBar.isVisible = true
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = characterAdapter
    }
}
