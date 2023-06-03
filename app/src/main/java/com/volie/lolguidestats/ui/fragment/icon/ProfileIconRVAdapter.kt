package com.volie.lolguidestats.ui.fragment.icon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.profile_icon.Icon
import com.volie.lolguidestats.databinding.ItemIntoAndFromBinding
import com.volie.lolguidestats.helper.Constant.PROFILE_ICON_IMAGE
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class ProfileIconRVAdapter(
    val onIconClick: (Icon) -> Unit
) : BaseAdapter<Icon>(IconDiffCallback()) {

    inner class ProfileIconViewHolder(private val binding: ItemIntoAndFromBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            val icon = currentList[position]

            Glide.with(binding.root)
                .load("${PROFILE_ICON_IMAGE}${icon.image.full}")
                .into(binding.ivItem)

            binding.root.setOnClickListener {
                onIconClick(icon)
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemIntoAndFromBinding.inflate(inflater, parent, false)
        return ProfileIconViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Icon, position: Int) {
        if (holder is ProfileIconViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class IconDiffCallback : DiffUtil.ItemCallback<Icon>() {
    override fun areItemsTheSame(oldItem: Icon, newItem: Icon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Icon, newItem: Icon): Boolean {
        return oldItem == newItem
    }
}
