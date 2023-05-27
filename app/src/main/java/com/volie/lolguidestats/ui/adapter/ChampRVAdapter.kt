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

class ChampRVAdapter : ListAdapter<Champion, ChampRVAdapter.ChampViewHolder>(
    ChampDiffCallback()
) {
    inner class ChampViewHolder(private val binding: ItemChampionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val champ = currentList[position]
            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}champion/${champ.image.full}")
                .into(binding.ivChampImageItem)
            binding.tvChampNameItem.text = champ.name

            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}passive/${champ.name}_P.png")
                .into(binding.ivPassive)

            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}spell/${champ.name}Q.png")
                .into(binding.ivSkillQ)

            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}spell/${champ.name}W.png")
                .into(binding.ivSkillW)

            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}spell/${champ.name}E.png")
                .into(binding.ivSkillE)

            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}spell/${champ.name}R.png")
                .into(binding.ivSkillR)
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
