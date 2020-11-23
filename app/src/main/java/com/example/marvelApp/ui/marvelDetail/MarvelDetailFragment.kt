package com.example.marvelApp.ui.marvelDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.marvelApp.R
import com.example.marvelApp.databinding.FragmentMarvelDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelDetailFragment : Fragment() {

    private val viewModel: MarvelDetailModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMarvelDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_marvel_detail, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}