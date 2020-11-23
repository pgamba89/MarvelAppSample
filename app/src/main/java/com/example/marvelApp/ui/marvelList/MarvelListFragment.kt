package com.example.marvelApp.ui.marvelList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelApp.R
import com.example.marvelApp.databinding.FragmentMarvelListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarvelListFragment : Fragment() {

    private val viewModel: MarvelListModelView by viewModels()

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMarvelListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_marvel_list, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = MarvelListAdapter(ListItemListener {

        })

        binding.recyclerviewlist.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewlist.adapter = adapter

/*        viewModel.marvelCharacters.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
                adapter.notifyDataSetChanged()
            }
        })*/

/*        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.marvelCharacters.observe(viewLifecycleOwner, {
                it?.let {
                    adapter.submitData(viewLifecycleOwner.lifecycle, it)
                }
            })
        }*/

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getCharacters()
                .collectLatest {
                    adapter.submitData(it)
                    adapter.notifyDataSetChanged()
                }
        }

        return binding.root
    }
}