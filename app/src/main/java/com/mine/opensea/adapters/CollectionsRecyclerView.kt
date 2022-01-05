package com.mine.opensea.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mine.opensea.*
import com.mine.opensea.database.models.Collection
import com.mine.opensea.databinding.CollectionRecyclerViewHolderBinding
import com.squareup.picasso.Picasso
import eightbitlab.com.blurview.RenderScriptBlur

class CollectionsRecyclerView() :
        ListAdapter<Collection, CollectionsRecyclerView.ViewHolder>(
            CollectionItemDiffCallback()
        ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.collection_recycler_view_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CollectionRecyclerViewHolderBinding.bind(itemView)
        public fun bindTo(collection: Collection) {
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
                .into(binding.bannerImageView, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        binding.rootView.setElevationShadowColor(binding.bannerImageView.getDominantColor())
                        binding.rootView.outlineAmbientShadowColor =
                            binding.bannerImageView.getDominantColor()
                    }

                    override fun onError(e: java.lang.Exception?) {
                        Log.d("setImage: ", "failed")
                    }
                })
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
            binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND;
            binding.blurView.clipToOutline = true;

        }
    }


    override fun submitList(list: List<Collection>?) {

        super.submitList(list)
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