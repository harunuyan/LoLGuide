package com.volie.lolguidestats.ui.fragment.icon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.profile_icon.Icon
import com.volie.lolguidestats.databinding.AdapterItemIntoAndFromBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class ProfileIconRVAdapter(private val onItemClick: (Icon) -> Unit) :
    BaseAdapter<Icon>(IconDiffCallback()) {

    inner class ProfileIconViewHolder(private val binding: AdapterItemIntoAndFromBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            val icon = currentList[position]

            Glide.with(binding.root)
                .load("${BASE_URL}img/profileicon/${icon.image.full}")
                .into(binding.ivItem)

            binding.root.setOnClickListener {
                onItemClick(icon)
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = AdapterItemIntoAndFromBinding.inflate(inflater, parent, false)
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