package com.mine.opensea.activities

import android.graphics.Color
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
import eightbitlab.com.blurview.RenderScriptBlur

import android.graphics.drawable.Drawable
import android.os.Build

import android.view.ViewGroup

import android.view.View
import android.view.ViewOutlineProvider
import androidx.compose.material.MaterialTheme.colors
import androidx.core.content.ContextCompat
import com.mine.opensea.R


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

        blur()

    }

    fun blur() {
        val radius = 5f

        val decorView = window.decorView
        //ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        //ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        //Set drawable to draw in the beginning of each blurred frame (Optional).
        //Can be used in case your layout has a lot of transparent space and your content
        //gets kinda lost after after blur is applied.
        //Set drawable to draw in the beginning of each blurred frame (Optional).
        //Can be used in case your layout has a lot of transparent space and your content
        //gets kinda lost after after blur is applied.
        val windowBackground = decorView.background

        binding.blurView.setupWith(binding.rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.blurView.clipToOutline = true;

        binding.blurView2.setupWith(binding.rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(25f)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
        binding.blurView2.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.blurView2.clipToOutline = true;
        //        binding.blurView.setOverlayColor(
        //           Color.argb(0.1f, 80f, 80f, 80f)
        //        )

    }

}



