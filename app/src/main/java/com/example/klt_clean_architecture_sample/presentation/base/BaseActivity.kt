package com.example.klt_clean_architecture_sample.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

typealias InflateActivity<T> = (LayoutInflater)->T

open class BaseActivity<VB : ViewBinding>(
    private val inflate: InflateActivity<VB>
) : AppCompatActivity() {
    private var _binding : VB? = null
    val binding get() = _binding!!

    open fun init(){}
    open fun setupListener(){}
    open fun observe(){}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate.invoke(layoutInflater)
        setContentView(binding.root)
        init()
        setupListener()
        observe()
    }
}