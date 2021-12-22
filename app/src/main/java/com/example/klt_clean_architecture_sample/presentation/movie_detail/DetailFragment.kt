package com.example.klt_clean_architecture_sample.presentation.movie_detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.databinding.FragmentDetailBinding
import com.example.klt_clean_architecture_sample.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailVM by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun init() {
        viewModel.getMovie(args.movieId)
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movie.collectLatest {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Timber.tag("movies-detail-loading").d(it.toString())
                    }
                    Resource.Status.ERROR -> {
                        Timber.tag("movies-detail-error").d(it.toString())
                    }
                    Resource.Status.SUCCESS -> {
                        Timber.tag("movies-detail-success").d(it.toString())
                    }
                }
            }
        }
    }
}