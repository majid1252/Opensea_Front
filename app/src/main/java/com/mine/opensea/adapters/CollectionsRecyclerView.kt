package com.mine.opensea.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mine.opensea.R
import com.mine.opensea.database.models.Collection
import com.mine.opensea.databinding.CollectionViewHolderBinding

class CollectionsRecyclerView() :
        ListAdapter<Collection, CollectionsRecyclerView.ViewHolder>(
            CollectionItemDiffCallback()
        ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.collection_view_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CollectionViewHolderBinding.bind(itemView)
        public fun bindTo(collection: Collection) {
            binding.nameTextView.text = collection.name
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