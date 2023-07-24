package com.volie.lolguidestats.ui.fragment.mission

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.mission.MissionData
import com.volie.lolguidestats.databinding.AdapterItemIntoAndFromBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class MissionAssetsRVAdapter(
    private val onItemClick: (MissionData) -> Unit
) : BaseAdapter<MissionData>(MissionAssetsDiffUtilCallback()) {

    inner class MissionAssetsViewHolder(private val binding: AdapterItemIntoAndFromBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val assets = currentList[position]
            Glide.with(binding.root)
                .load("${BASE_URL}img/mission/${assets.image?.full}")
                .into(binding.ivItem)

            binding.root.setOnClickListener {
                onItemClick(assets)
            }
        }

    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = AdapterItemIntoAndFromBinding.inflate(inflater, parent, false)
        return MissionAssetsViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: MissionData, position: Int) {
        if (holder is MissionAssetsViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class MissionAssetsDiffUtilCallback : DiffUtil.ItemCallback<MissionData>() {
    override fun areItemsTheSame(oldItem: MissionData, newItem: MissionData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MissionData, newItem: MissionData): Boolean {
        return oldItem == newItem
    }
}
