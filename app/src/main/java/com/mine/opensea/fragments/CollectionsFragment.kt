package com.mine.opensea.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.mine.opensea.adapters.CollectionsRecyclerViewAdapter
import com.mine.opensea.databinding.FragmentCollectionsBinding
import com.mine.opensea.viewModels.CollectionsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class CollectionsFragment : Fragment() {

    private val collectionsFragmentVM: CollectionsFragmentViewModel by viewModels()
    private val binding: FragmentCollectionsBinding by lazy {
        FragmentCollectionsBinding.inflate(layoutInflater)
    }

    companion object {
        const val TAG = "fragment_collection"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val mAdapter = CollectionsRecyclerViewAdapter(this)
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        collectionsFragmentVM.collectionsPagingLive.observe(this, { it ->
            mAdapter.submitData(lifecycle, it)
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}