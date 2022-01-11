package com.example.klt_clean_architecture_sample.presentation.movie_list

import android.opengl.Visibility
import android.util.Log
import android.view.View
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
                        Log.d("reach001",it.data?.size.toString())
                        binding.recycler.visibility = View.GONE
                        binding.loading.retryLayout.visibility = View.VISIBLE
                        binding.loading.progress.visibility = View.VISIBLE

                    }
                    Resource.Status.ERROR -> {
                        Log.d("reach002",it.data?.size.toString())
                        binding.recycler.visibility = View.GONE
                        binding.loading.retryLayout.visibility = View.VISIBLE
                        binding.loading.progress.visibility = View.GONE
                        binding.loading.errorText.visibility = View.VISIBLE


                        binding.loading.errorText.text = it.message

                    }
                    Resource.Status.SUCCESS -> {
                        Log.d("reach003",it.data?.size.toString())
                        binding.recycler.visibility = View.VISIBLE
                        binding.loading.retryLayout.visibility = View.GONE
                        binding.loading.progress.visibility = View.GONE
                        binding.loading.errorText.visibility = View.GONE

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