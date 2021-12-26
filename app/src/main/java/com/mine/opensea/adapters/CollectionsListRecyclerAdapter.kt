package com.mine.opensea.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mine.opensea.R
import com.mine.opensea.database.models.CollectionModel
import com.mine.opensea.databinding.CollectionViewHolderBinding

class CollectionsListRecyclerAdapter() :
        ListAdapter<CollectionModel, CollectionsListRecyclerAdapter.ViewHolder>(
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
        public fun bindTo(collectionModel: CollectionModel) {
            binding.nameTextView.text = collectionModel.name
        }
    }

    class CollectionItemDiffCallback : DiffUtil.ItemCallback<CollectionModel>() {
        override fun areItemsTheSame(oldItem: CollectionModel, newItem: CollectionModel): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
                oldItem: CollectionModel,
                newItem: CollectionModel
        ): Boolean = oldItem == newItem

    }
}