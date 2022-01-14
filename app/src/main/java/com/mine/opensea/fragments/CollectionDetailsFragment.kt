package com.mine.opensea.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.mine.opensea.OpenseaApplication
import com.mine.opensea.R
import com.mine.opensea.database.models.Collection
import com.mine.opensea.databinding.FragmentCollectionDetailsBinding
import com.mine.opensea.onInitialized
import com.mine.opensea.viewModels.CollectionDetailsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.coroutines.delay
import java.lang.Exception

@AndroidEntryPoint
class CollectionDetailsFragment : Fragment(R.layout.fragment_collection_details) {

    private val collectionDetailsViewModel: CollectionDetailsViewModel by viewModels()
    private val binding: FragmentCollectionDetailsBinding by lazy {
        FragmentCollectionDetailsBinding.inflate(layoutInflater)
    }

    companion object {
        const val TAG = "FRAGMENT_COLLECTIONS_DETAILS"
        const val TRANSLATION_ARG = "FRAGMENT_COLLECTIONS_DETAILS"
        const val PARCEL_ARG = "COLLECTION"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding.collectionBannerImageView.transitionName = arguments?.getString(TRANSLATION_ARG)
        val collection = arguments?.getParcelable<Collection>(PARCEL_ARG)
        Picasso.get()
            .load(collection?.bannerImageUrl)
            .into(binding.collectionBannerImageView, object : Callback {
                override fun onSuccess() {
                    binding.collectionBannerImageView.onInitialized {
                        binding.dynamicColorBack.apply {
                            diversifyBack = 2
                            iterationCount = 25
                            _alpha = 0.18F
                            bitmapBackground =
                                binding.collectionBannerImageView.drawToBitmap(Bitmap.Config.ARGB_8888)
                        }.draw()
                    }
                }

                override fun onError(e: Exception?) {

                }

            })

        Picasso.get()
            .load(collection?.imageUrl)
            .into(binding.imageView)
        binding.nameTextView.text = collection?.name
        binding.descriptionTextView.text = collection?.description
        setUpMotion()
        setUpBlur()
        fetchData(collection!!.slug)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun fetchData(slug: String) {
        collectionDetailsViewModel.getCollectionDetails(slug).observe(this, Observer {

        })
        collectionDetailsViewModel.getCollectionStats(slug).observe(this, Observer {
            it?.stats?.let { stats ->
                for (valueName in listOf(
                    "Total Sale",
                    "Total Count",
                    "Total Volume",
                    "Daily Volume",
                    "Weekly Volume",
                    "Monthly Volume",
                )) {
                    var value: Any? = 0.0
                    var unit = ""
                    when (valueName) {
                        "Total Sale" -> value = stats.totalSales?.toInt()
                        "Total Count" -> value = stats.count?.toInt()
                        "Total Volume" -> {
                            value = stats.totalVolume
                            unit = "ETH"
                        }
                        "Daily Volume" -> {
                            value = stats.oneDayVolume
                            unit = "ETH"
                        }
                        "Weekly Volume" -> {
                            value = stats.sevenDayVolume
                            unit = "ETH"
                        }
                        "Monthly Volume" -> {
                            value = stats.thirtyDayVolume
                            unit = "ETH"
                        }
                    }
                    val v = layoutInflater.inflate(
                        R.layout.collection_stats_row,
                        binding.statsLinearLayout,
                        false
                    )
                    v.findViewById<TextView>(R.id.value_name_text_view).text = "$valueName :"
                    v.findViewById<TextView>(R.id.value_text_view).text = value.toString()
                    v.findViewById<TextView>(R.id.value_unit_text_view).text = " $unit"
                    binding.statsLinearLayout.addView(v)
                }
            }
        })
    }


    private fun setUpMotion() {
        enterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.collection_shared_exit_transition)
        exitTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.collection_shared_exit_transition)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.collection_details_shared_element_transition)
        enterAnimations()
    }

    private fun enterAnimations() {
        binding.detailsBlurView.animate().apply {
            translationYBy(200F)
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
            setUpdateListener {
                val lp: ConstraintLayout.LayoutParams =
                    binding.pagerBlurView.layoutParams as ConstraintLayout.LayoutParams
                lp.topMargin = (25F * it.animatedValue as Float).toInt()
                binding.pagerBlurView.layoutParams = lp
                val lp2: ConstraintLayout.LayoutParams =
                    binding.descriptionBlurView.layoutParams as ConstraintLayout.LayoutParams
                lp2.topMargin = (225F * it.animatedValue as Float).toInt()
                binding.descriptionBlurView.layoutParams = lp2
            }

        }.setStartDelay(350).start()
    }

    private fun setUpBlur() {
        val radius = 25f
        binding.backBlur.setupWith(binding.rootView)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)

        binding.descriptionBlurView.setupWith(binding.rootView)
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
        binding.descriptionBlurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.descriptionBlurView.clipToOutline = true;

        binding.pagerBlurView.setupWith(binding.rootView)
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
        binding.pagerBlurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.pagerBlurView.clipToOutline = true;

        binding.detailsBlurView.setupWith(binding.container)
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
        binding.detailsBlurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
        binding.detailsBlurView.clipToOutline = true;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}