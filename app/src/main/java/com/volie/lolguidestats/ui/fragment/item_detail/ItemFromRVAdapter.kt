package com.volie.lolguidestats.ui.fragment.item_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.ItemIntoAndFromBinding
import com.volie.lolguidestats.helper.Constant.CHAMPION_IMAGE_URL
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class ItemFromRVAdapter(
    private val onItemClick: () -> Unit
) : BaseAdapter<String>(ItemFromDiffCallback()) {

    inner class ItemFromViewHolder(private val binding: ItemIntoAndFromBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val itemFrom = currentList[position]

            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}item/${itemFrom}.png")
                .into(binding.ivItem)

            binding.root.setOnClickListener {
                onItemClick()
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemIntoAndFromBinding.inflate(inflater, parent, false)
        return ItemFromViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: String, position: Int) {
        if (holder is ItemFromViewHolder) {
            holder.bind(position)
        }
    }
}

class ItemFromDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
