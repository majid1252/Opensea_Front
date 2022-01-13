package com.mine.opensea.adapters

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Interpolator
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mine.opensea.*
import com.mine.opensea.database.models.Collection
import com.mine.opensea.databinding.CollectionRecyclerViewHolderBinding
import com.mine.opensea.fragments.CollectionDetailsFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import eightbitlab.com.blurview.RenderScriptBlur
import kotlin.math.absoluteValue
import kotlin.math.sign

class CollectionsRecyclerViewAdapter(
        val fragment: Fragment,
        private val recyclerView: RecyclerView
) :
        PagingDataAdapter<Collection, CollectionsRecyclerViewAdapter.ViewHolder>(
            CollectionItemDiffCallback()
        ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.collection_recycler_view_holder, parent, false),
            fragment,
            recyclerView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position)!!, holder)
    }

    class ViewHolder(itemView: View, private val fragment: Fragment, recyclerView: RecyclerView) :
            RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnTouchListener {

        private val binding = CollectionRecyclerViewHolderBinding.bind(itemView)
        private val collectionDetailsFragment: CollectionDetailsFragment by lazy {
            CollectionDetailsFragment()
        }

        init {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    Log.d("OnScrolled:", "dx= $dx dy= $dy")
                    itemView.animate().apply {
                        translationY(-1 * dy.sign * minOf(dy.absoluteValue, 80).toFloat())
                        withEndAction {
                            if (itemView.translationY.absoluteValue > 30) {
                                itemView.animate().apply {
                                    translationY(0F)
                                    duration = 200
                                    interpolator = AccelerateDecelerateInterpolator()
                                }
                            }
                        }
                    }
                        .setStartDelay(
                            minOf(itemView.absY().absoluteValue.div(10).toLong(), 20)
                        )
                        .start()
                    Log.d("Delay:", itemView.absY().absoluteValue.div(5).toString())
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    Log.d("OnScrolledState:", itemView.translationY.toString())
                }
            })
        }

        @SuppressLint("ClickableViewAccessibility")
        fun bindTo(collection: Collection, holder: ViewHolder) {
            blur(binding = binding)
            binding.nameTextView.text = collection.name
            binding.nameTextView.isSelected = true
            binding.descriptionTextView.isSelected = true
            binding.descriptionTextView.text = collection.description
            binding.bannerImageView.clipToOutline = true
            binding.rootView.clipToOutline = true
            Picasso.get()
                .load(collection.imageUrl)
                .placeholder(R.drawable.image_holder_one)
                .error(R.drawable.image_holder_one)
                .into(binding.imageView)
            Picasso.get()
                .load(collection.bannerImageUrl)
                .placeholder(ExtFunctions.getRandomDrawable())
                .error(ExtFunctions.getRandomDrawable())
                .into(binding.bannerImageView, object : Callback {
                    override fun onSuccess() {
                        binding.rootView.setElevationShadowColor(binding.bannerImageView.getDominantColor())
                        binding.rootView.outlineAmbientShadowColor =
                            binding.bannerImageView.getDominantColor()
                    }

                    override fun onError(e: java.lang.Exception?) {
                    }
                })

            binding.bannerImageView.transitionName =
                collection.slug.replace("-", "")
            val bundle = Bundle().apply {
                putString(
                    CollectionDetailsFragment.TRANSLATION_ARG,
                    binding.bannerImageView.transitionName
                )
                putParcelable(
                    CollectionDetailsFragment.PARCEL_ARG,
                    collection
                )
            }

            collectionDetailsFragment.arguments = bundle
            binding.rootView.setOnClickListener(this)
            binding.rootView.setOnTouchListener(this)

        }

        private fun blur(binding: CollectionRecyclerViewHolderBinding) {
            val radius = 25f
            binding.blurView.setupWith(binding.rootView)
                .setBlurAlgorithm(RenderScriptBlur(OpenseaApplication.context))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setOverlayColor(
                    ContextCompat.getColor(
                        OpenseaApplication.context,
                        R.color.white_overlay
                    )
                )
                .setHasFixedTransformationMatrix(false)
            binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
            binding.blurView.clipToOutline = true

        }

        override fun onClick(p0: View?) {
            fragment.parentFragmentManager.commit {
                setReorderingAllowed(true)
                addSharedElement(
                    binding.bannerImageView,
                    binding.bannerImageView.transitionName
                )
                replace(R.id.fragment_holder, collectionDetailsFragment)
                addToBackStack(null)
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            p1?.let {
                p0?.let {
                    Log.d("TouchItem.OnIntercept", p1.action.toString())
                    Log.d("TouchItem.ScaleX", p0.scaleX.toString())
                    when (p1.action) {
                        0 ->
                            if (p0.scaleX == 1.0F) {
                                it.animate().apply {
                                    scaleXBy(-0.05F)
                                    scaleYBy(-0.05F)
                                    duration = 300
                                    interpolator = AccelerateDecelerateInterpolator()
                                }.setStartDelay(150).start()
                                binding.bannerImageView.animate().apply {
                                    scaleXBy(0.03F)
                                    scaleYBy(0.03F)
                                    duration = 300
                                    interpolator = AccelerateDecelerateInterpolator()
                                }.setStartDelay(150).start()
                            }
                        3 -> {
                            it.clearAnimation()
                            it.animate().apply {
                                scaleY(1F)
                                scaleX(1F)
                                duration = 300
                                interpolator = AccelerateDecelerateInterpolator()
                            }.start()
                            binding.bannerImageView.clearAnimation()
                            binding.bannerImageView.animate().apply {
                                scaleY(1F)
                                scaleX(1F)
                                duration = 300
                                interpolator = AccelerateDecelerateInterpolator()
                            }.setStartDelay(150).start()
                        }
                    }
                }
            }
            return false
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    class CollectionItemDiffCallback : DiffUtil.ItemCallback<Collection>() {
        override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
                oldItem: Collection,
                newItem: Collection
        ): Boolean = oldItem == newItem

    }
}