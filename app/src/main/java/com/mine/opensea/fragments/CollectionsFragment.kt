package com.mine.opensea.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mine.opensea.R
import com.mine.opensea.adapters.CollectionsRecyclerView
import com.mine.opensea.databinding.FragmentCollectionsBinding
import com.mine.opensea.viewModels.CollectionsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionsFragment : Fragment() {

    private val collectionsFragmentVM: CollectionsFragmentViewModel by viewModels()
    private val binding: FragmentCollectionsBinding by lazy {
        FragmentCollectionsBinding.inflate(layoutInflater)
    }

    companion object {
        const val TAG = "fragment_collection"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = CollectionsRecyclerView()
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        collectionsFragmentVM.collectionsModel.observe(
            this,
            Observer {
                mAdapter.submitList(it)
            })
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}