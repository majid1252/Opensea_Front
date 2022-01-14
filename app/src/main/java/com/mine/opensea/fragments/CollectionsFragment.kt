package com.mine.opensea.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mine.opensea.adapters.CollectionsRecyclerViewAdapter
import com.mine.opensea.databinding.FragmentCollectionsBinding
import com.mine.opensea.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.coroutines.flow.collect

@ExperimentalPagingApi
@AndroidEntryPoint
class CollectionsFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
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

        val mAdapter = CollectionsRecyclerViewAdapter(this, binding.recyclerView)
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        mainViewModel.getCollections().observe(this, {
            mAdapter.submitData(lifecycle, it)
        })
        binding.blurView.setupWith(binding.rootView)
            .setBlurAutoUpdate(true)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setHasFixedTransformationMatrix(false)
            .setBlurRadius(20F)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }


}