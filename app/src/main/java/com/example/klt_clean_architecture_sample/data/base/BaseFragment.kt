package com.example.klt_clean_architecture_sample.data.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias InflateFragment<T> = (LayoutInflater,ViewGroup?,Boolean)->T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: InflateFragment<VB>
) : Fragment() {
    private var _binding : ViewBinding? = null
    val binding get() = _binding!!

    abstract fun init()
    abstract fun setupView()
    abstract fun observe()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupView()
        observe()
    }
}