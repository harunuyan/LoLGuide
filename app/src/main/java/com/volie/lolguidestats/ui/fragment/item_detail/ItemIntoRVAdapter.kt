package com.volie.lolguidestats.ui.fragment.item_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.databinding.ItemIntoAndFromBinding
import com.volie.lolguidestats.helper.Constant.CHAMPION_IMAGE_URL
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class ItemIntoRVAdapter(
    private val onItemClick: () -> Unit
) : BaseAdapter<String>(ItemIntoDiffCallback()) {

    inner class ItemIntoViewHolder(private val binding: ItemIntoAndFromBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val itemInto = currentList[position]
            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}item/${itemInto}.png")
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
        return ItemIntoViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: String, position: Int) {
        if (holder is ItemIntoViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class ItemIntoDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
