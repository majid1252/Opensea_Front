package com.mine.opensea.fragments

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import com.mine.opensea.OpenseaApplication
import com.mine.opensea.R
import com.mine.opensea.adapters.CollectionsRecyclerViewAdapter
import com.mine.opensea.databinding.FragmentCollectionDetailsBinding
import com.mine.opensea.databinding.FragmentCollectionsBinding
import com.mine.opensea.onInitialized
import com.mine.opensea.utilities.DynamicColorBackground
import com.mine.opensea.viewModels.CollectionsFragmentViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import eightbitlab.com.blurview.RenderScriptBlur
import java.lang.Exception

class CollectionDetailsFragment : Fragment(R.layout.fragment_collection_details) {

    private val binding: FragmentCollectionDetailsBinding by lazy {
        FragmentCollectionDetailsBinding.inflate(layoutInflater)
    }

    companion object {
        const val TAG = "fragment_collection_detail"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding.collectionBannerImageView.transitionName = arguments?.getString("TRANS_NAME")
        Picasso.get()
            .load(arguments?.getString("IMAGE_URI"))
            .into(binding.collectionBannerImageView, object : Callback {
                override fun onSuccess() {
                    binding.collectionBannerImageView.onInitialized {
                        binding.dynamicColorBack.apply {
                            diversifyBack = 4
                            iteratinoCount = 20
                            _alpha = 0.25F
                            bitmapBackground =
                                binding.collectionBannerImageView.drawToBitmap(Bitmap.Config.ARGB_8888)
                        }.draw()
                    }
                }

                override fun onError(e: Exception?) {

                }

            })
        enterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.collection_shared_exit_transition)
        exitTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.collection_shared_exit_transition)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.collection_details_shared_element_transition)
        enterAnimations()
        setUpBlur()
        return binding.root
    }

    private fun enterAnimations() {
        binding.detailsBlurView.animate().apply {
            translationYBy(200F)
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            setUpdateListener {
                val lp: ConstraintLayout.LayoutParams =
                    binding.blurView2.layoutParams as ConstraintLayout.LayoutParams
                lp.topMargin = (300F * it.animatedValue as Float).toInt()
                binding.blurView2.layoutParams = lp
            }

        }.start()
    }

    private fun setUpBlur() {
        val radius = 25f
        binding.backBlur.setupWith(binding.rootView)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)

        binding.blurView2.setupWith(binding.rootView)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setOverlayColor(
                ContextCompat.getColor(
                    OpenseaApplication.context,
                    R.color.white_overlay
                )
            )
            .setHasFixedTransformationMatrix(false)
        binding.blurView2.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.blurView2.clipToOutline = true;

        binding.detailsBlurView.setupWith(binding.container)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setBlurRadius(5F)
            .setBlurAutoUpdate(false)
            .setOverlayColor(
                ContextCompat.getColor(
                    OpenseaApplication.context,
                    R.color.white_overlay
                )
            )
            .setHasFixedTransformationMatrix(false)
        binding.detailsBlurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.detailsBlurView.clipToOutline = true;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}