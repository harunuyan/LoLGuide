package com.volie.lolguidestats.ui.fragment.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.map.Map
import com.volie.lolguidestats.databinding.ItemMapsBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class MapsRVAdapter : BaseAdapter<Map>(MapDiffUtilCallback()) {

    inner class MapsViewHolder(private val binding: ItemMapsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            val maps = currentList[position]

            Glide.with(binding.root)
                .load("${BASE_URL}img/map/${maps.image.full}")
                .into(binding.ivMaps)

            binding.tvMaps.text = maps.mapName
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemMapsBinding.inflate(inflater, parent, false)
        return MapsViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Map, position: Int) {
        if (holder is MapsViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class MapDiffUtilCallback : DiffUtil.ItemCallback<Map>() {
    override fun areItemsTheSame(oldItem: Map, newItem: Map): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Map, newItem: Map): Boolean {
        return oldItem == newItem
    }
}
