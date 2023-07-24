package com.volie.lolguidestats.ui.fragment.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.item.Item
import com.volie.lolguidestats.databinding.AdapterItemItemBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL

class ItemRVAdapter(
    private val onItemClick: (Item) -> Unit
) : ListAdapter<Item, ItemRVAdapter.ItemViewHolder>(
    ItemDiffCallback()
) {
    inner class ItemViewHolder(private val binding: AdapterItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = currentList[position]

            with(binding) {
                Glide.with(root)
                    .load("${BASE_URL}img/item/${item.itemImage.full}")
                    .into(ivItemImage)

                tvItemName.text = item.name

                tvItemPlain.text = item.plaintext

                if (item.gold?.base != 0) {
                    tvGold.text = item.gold?.total.toString()
                } else {
                    tvGold.visibility = View.GONE
                    ivGold.visibility = View.GONE
                }

                tvItemTags.text = item.tags.toString().trim('[', ']')

                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding =
            AdapterItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
