package com.mine.opensea.activities

import android.animation.ObjectAnimator
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.mine.opensea.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

import android.view.ViewOutlineProvider
import androidx.compose.animation.fadeOut
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mine.opensea.*
import com.mine.opensea.fragments.AssetsFragment
import com.mine.opensea.fragments.BundlesFragment
import com.mine.opensea.fragments.CollectionsFragment
import com.mine.opensea.fragments.MyAssetsFragment
import com.mine.opensea.utilities.OpenRecyclerView


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bundlesFragment = BundlesFragment()
    private val collectionsFragment = CollectionsFragment()
    private val assetsFragment = AssetsFragment()
    private val myAssetsFragment = MyAssetsFragment()

    companion object {
        private const val DEBUG_TAG = "Gestures"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainBottomNavigation.itemRippleColor =
            ContextCompat.getColorStateList(this, android.R.color.transparent)
        OpenRecyclerView.addGlobalFlingListener(object : RecyclerView.OnFlingListener() {
            override fun onFling(velocityX: Int, velocityY: Int): Boolean {
                if (velocityY > 0) {
                    binding.rl.hide()
                } else
                    binding.rl.show()
                return false
            }

        })
        setUp(savedInstanceState)
    }

    private fun setUp(savedInstanceState: Bundle?) {
        setUpBottomNavigation()
        setUpBlur()
        addFragments(savedInstanceState)
    }

    private fun setUpBottomNavigation() {

        // waiting for view to get initialized in order to execute translationX...
        // animations successfully for the first time
        findViewById<View>(R.id.tab_bundles).onInitialized {
            binding.mainBottomNavigation.selectedItemId = R.id.tab_bundles
        }

        binding.mainBottomNavigation.setOnItemSelectedListener { item ->
            val viewItem = findViewById<View>(item.itemId)
            val animatePosition = viewItem.absX().toFloat() + viewItem.width / 10

            animateToPositionX(binding.itemMenuBackBlur, animatePosition)
            animateToPositionX(binding.image, animatePosition)

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

    private fun animateToPositionX(toAnimate: View, animatePosition: Float) {
        ObjectAnimator.ofFloat(
            toAnimate,
            "translationX",
            animatePosition
        ).apply {
            duration = 200
            start()
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

    private fun setUpBlur() {
        val radius = 25f
        val decorView = window.decorView
        val windowBackground = decorView.background
        binding.blurView.setupWith(binding.rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.blurView.clipToOutline = true;

    }

    private fun ConstraintLayout.hide() {
        if (alpha == 1f)
            animate().apply {
                duration = 200
                translationYBy(200f)
                alpha(0f)
            }.start()
    }

    private fun ConstraintLayout.show() {
        if (alpha == 0f)
            animate().apply {
                duration = 200
                translationYBy(-200f)
                alpha(1f)
            }.start()
    }

}



