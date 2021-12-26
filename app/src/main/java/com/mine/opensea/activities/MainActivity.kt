package com.mine.opensea.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mine.opensea.adapters.CollectionsListRecyclerAdapter
import com.mine.opensea.databinding.ActivityMainBinding
import com.mine.opensea.viewModels.CollectionListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val collectionListViewModel: CollectionListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter = CollectionsListRecyclerAdapter()

        binding.collectionRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        collectionListViewModel.collectionsModel.observe(
            this,
            Observer {
                mAdapter.submitList(it)
            })

    }

}



