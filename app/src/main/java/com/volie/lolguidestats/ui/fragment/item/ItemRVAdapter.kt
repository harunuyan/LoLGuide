package com.volie.lolguidestats.ui.fragment.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.item.Item
import com.volie.lolguidestats.databinding.ItemItemBinding
import com.volie.lolguidestats.helper.Constant.ITEM_IMAGE

class ItemRVAdapter(
    private val onItemClick: (Item) -> Unit
) : ListAdapter<Item, ItemRVAdapter.ItemViewHolder>(
    ItemDiffCallback()
) {
    inner class ItemViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = currentList[position]

            Glide.with(binding.root)
                .load("${ITEM_IMAGE}${item.itemImage?.full}")
                .into(binding.ivItemImage)

            binding.tvItemName.text = item.name

            binding.tvItemPlain.text = item.plaintext

            if (item.gold?.base != 0) {
                binding.tvGold.text = item.gold?.total.toString()
            } else {
                binding.tvGold.visibility = View.GONE
                binding.ivGold.visibility = View.GONE
            }

            binding.tvItemTags.text = item.tags.toString().trim('[', ']')

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}
