package com.mine.opensea.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mine.opensea.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

import android.view.ViewOutlineProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.mine.opensea.R
import com.mine.opensea.fragments.AssetsFragment
import com.mine.opensea.fragments.BundlesFragment
import com.mine.opensea.fragments.CollectionsFragment
import com.mine.opensea.fragments.MyAssetsFragment


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bundlesFragment = BundlesFragment()
    private val collectionsFragment = CollectionsFragment()
    private val assetsFragment = AssetsFragment()
    private val myAssetsFragment = MyAssetsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addFragments(savedInstanceState)
        blur()
        binding.mainBottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.tab_bundles -> {
                    showFragment(bundlesFragment)
                    true
                }
                R.id.tab_collections -> {
                    showFragment(collectionsFragment)
                    true
                }
                R.id.tab_assets -> {
                    showFragment(assetsFragment)
                    true
                }
                R.id.tab_my_assets -> {
                    showFragment(myAssetsFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
            if (fragment !is BundlesFragment) hide(bundlesFragment)
            if (fragment !is AssetsFragment) hide(assetsFragment)
            if (fragment !is CollectionsFragment) hide(collectionsFragment)
            if (fragment !is MyAssetsFragment) hide(myAssetsFragment)
            show(fragment)
        }
    }

    private fun addFragments(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_holder, bundlesFragment, BundlesFragment.TAG)
                add(R.id.fragment_holder, collectionsFragment, CollectionsFragment.TAG)
                add(R.id.fragment_holder, assetsFragment, AssetsFragment.TAG)
                add(R.id.fragment_holder, myAssetsFragment, MyAssetsFragment.TAG)
            }
            showFragment(bundlesFragment)
        }
    }

    private fun blur() {
        val radius = 25f
        val decorView = window.decorView
        val windowBackground = decorView.background
        binding.blurView.setupWith(binding.rl)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.blurView.clipToOutline = true;

    }

}



