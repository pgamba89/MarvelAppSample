package com.example.marvelApp.ui.marvelList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelApp.R
import com.example.marvelApp.databinding.FragmentMarvelListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarvelListFragment : Fragment() {

    private val viewModel: MarvelListModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMarvelListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_marvel_list, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = MarvelListAdapter(ListItemListener {
            view?.findNavController()
                ?.navigate(
                    MarvelListFragmentDirections.actionMarvelListFragmentToMarvelDetailFragment(it)
                )
        })

        binding.recyclerviewlist.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewlist.adapter = adapter

        viewModel.marvelCharacters?.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        })

        viewModel.errorConnection.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(activity, R.string.NoConnection, Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }
}