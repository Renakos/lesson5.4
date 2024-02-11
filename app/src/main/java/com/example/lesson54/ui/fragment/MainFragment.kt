package com.example.lesson54.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson54.data.model.Character
import com.example.lesson54.data.viewmodel.MainViewModel
import com.example.lesson54.databinding.FragmentMainBinding
import com.example.lesson54.ui.adapter.CharacterAdapter
import com.example.lesson54.utils.UiState

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter
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

        setupRecyclerView()

        viewModel.uiState.observe(viewLifecycleOwner) {
            handleUiState(it)
        }

        viewModel.fetchData()
    }

    private fun setupRecyclerView() {
        characterAdapter = CharacterAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = characterAdapter
    }

    private fun handleUiState(uiState: UiState<List<Character>>) {
        if (uiState.isLoading) {
            Log.e("loading", "success")
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            binding.errorTextView.visibility = View.GONE
        } else if (uiState.success != null) {
            Log.e("success", "success")
            characterAdapter.updateData(uiState.success)
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            binding.errorTextView.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
            binding.errorTextView.visibility = View.VISIBLE
            binding.errorTextView.text = uiState.errorMessage ?: "Неизвестная ошибка"
        }
    }
}
