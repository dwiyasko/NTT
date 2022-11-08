package com.example.ntttest.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ntttest.databinding.FragmentHomeBinding
import com.example.ntttest.home.presentation.adapter.BookAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    var binding: FragmentHomeBinding? = null

    var bookAdapter: BookAdapter? = null

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter()

        binding?.apply {
            bookList.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = bookAdapter
            }
        }

        observeData()
    }

    override fun onDestroyView() {
        bookAdapter = null
        super.onDestroyView()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.loadBooks().collectLatest {
                bookAdapter?.submitData(it)
            }
        }
    }
}
