package com.mine.opensea.fragments

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.core.view.ViewCompat
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
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
        setUpBlur()
        return binding.root
    }

    private fun setUpBlur() {
        val radius = 25f
        binding.blurView.setupWith(binding.rootView)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(false)
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.blurView.clipToOutline = true;

        binding.blurView2.setupWith(binding.rootView)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(false)
        binding.blurView2.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.blurView2.clipToOutline = true;

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}