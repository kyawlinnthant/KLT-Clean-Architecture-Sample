package com.example.klt_clean_architecture_sample.presentation.movie_list

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.databinding.FragmentListBinding
import com.example.klt_clean_architecture_sample.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListVM by viewModels()

    private val adapter: MovieListAdapter by lazy {
        MovieListAdapter { getItemClick(it) }.apply {
            binding.recycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recycler.adapter = this
        }
    }

    override fun observe() {

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movies.collectLatest {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        binding.recycler.isVisible = false

                    }
                    Resource.Status.ERROR -> {
                        binding.loading.progress.isVisible = false
                        binding.loading.errorText.text = it.message

                    }
                    Resource.Status.SUCCESS -> {
                        binding.loading.progress.isVisible = false
                        binding.loading.errorText.isVisible = false
                        binding.loading.retryButton.isVisible = false
                        binding.recycler.isVisible = true
                        adapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun getItemClick(position: Int) {
        val item = adapter.getClickItem(position)
        val action = ListFragmentDirections.actionListToDetail(item.id)
        findNavController().navigate(action)
    }
}