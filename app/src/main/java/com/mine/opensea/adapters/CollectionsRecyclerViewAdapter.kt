package com.mine.opensea.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
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
            RecyclerView.ViewHolder(itemView) {

        private val binding = CollectionRecyclerViewHolderBinding.bind(itemView)
        private val collectionDetailsFragment: CollectionDetailsFragment by lazy {
            CollectionDetailsFragment()
        }

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
                putString("TRANS_NAME", binding.bannerImageView.transitionName)
                putString("IMAGE_URI", collection.bannerImageUrl)
            }

            collectionDetailsFragment.arguments = bundle
            binding.rootView.setOnClickListener {
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