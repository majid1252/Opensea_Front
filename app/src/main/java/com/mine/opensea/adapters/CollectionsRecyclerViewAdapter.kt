package com.mine.opensea.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
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

class CollectionsRecyclerViewAdapter(val fragment: Fragment) :
        PagingDataAdapter<Collection, CollectionsRecyclerViewAdapter.ViewHolder>(
            CollectionItemDiffCallback()
        ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.collection_recycler_view_holder, parent, false),
            fragment
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position)!!, holder)
    }

    class ViewHolder(itemView: View, private val fragment: Fragment) :
            RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnTouchListener {

        private val binding = CollectionRecyclerViewHolderBinding.bind(itemView)
        private val collectionDetailsFragment: CollectionDetailsFragment by lazy {
            CollectionDetailsFragment()
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

        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            p1?.let {
                p0?.let {
                    Log.d("TouchItem.OnIntercept", p1.action.toString())
                    Log.d("TouchItem.ScaleX", p0.scaleX.toString())
                    when (p1.action) {
                        0 -> if (p0.scaleX == 1.0F)
                            it.animate().apply {
                                scaleXBy(-0.05F)
                                scaleYBy(-0.05F)
                                duration = 300
                                interpolator = AccelerateDecelerateInterpolator()
                            }.setStartDelay(150).start()
                        3 -> {
                            it.clearAnimation()
                            it.animate().apply {
                                scaleY(1F)
                                scaleX(1F)
                                duration = 300
                                interpolator = AccelerateDecelerateInterpolator()
                            }.start()
                        }
                    }
                }
            }
            return false
        }
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