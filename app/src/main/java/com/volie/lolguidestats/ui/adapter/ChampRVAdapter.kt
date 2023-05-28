package com.volie.lolguidestats.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.champion.Champion
import com.volie.lolguidestats.databinding.ItemChampionBinding
import com.volie.lolguidestats.helper.Constant.CHAMPION_IMAGE_URL

class ChampRVAdapter(
    val onChampClick: (Champion) -> Unit
) : ListAdapter<Champion, ChampRVAdapter.ChampViewHolder>(
    ChampDiffCallback()
) {
    inner class ChampViewHolder(private val binding: ItemChampionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val champ = currentList[position]
            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}champion/${champ.image?.full}")
                .into(binding.ivChampImageItem)
            binding.tvChampNameItem.text = champ.name

            binding.root.setOnClickListener {
                onChampClick(champ)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChampRVAdapter.ChampViewHolder {
        val binding =
            ItemChampionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChampViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampRVAdapter.ChampViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}

class ChampDiffCallback : DiffUtil.ItemCallback<Champion>() {
    override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem == newItem
    }
}
