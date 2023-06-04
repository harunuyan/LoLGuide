package com.volie.lolguidestats.ui.fragment.summoner_spell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.summoner_spell.SummonerSpell
import com.volie.lolguidestats.databinding.ItemSummonerSpellBinding
import com.volie.lolguidestats.helper.Constant.CHAMPION_IMAGE_URL
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class SummonerSpellRVAdapter : BaseAdapter<SummonerSpell>(SummonerSpellDiffCallback()) {

    inner class SummonerSpellViewHolder(private val binding: ItemSummonerSpellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val spell = currentList[position]
            Glide.with(binding.root)
                .load("${CHAMPION_IMAGE_URL}spell/${spell.id}.png")
                .into(binding.ivItemImage)

            binding.tvItemName.text = spell.name
            binding.tvItemPlain.text = spell.description
            binding.tvLevel.text = "Level ${spell.summonerLevel.toString()}"
            binding.tvItemMode.text = spell.modes.toString().trim('[', ']')
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemSummonerSpellBinding.inflate(inflater, parent, false)
        return SummonerSpellViewHolder(binding)
    }

    override fun bindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: SummonerSpell,
        position: Int
    ) {
        if (holder is SummonerSpellViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class SummonerSpellDiffCallback : DiffUtil.ItemCallback<SummonerSpell>() {
    override fun areItemsTheSame(oldItem: SummonerSpell, newItem: SummonerSpell): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SummonerSpell, newItem: SummonerSpell): Boolean {
        return oldItem == newItem
    }
}
