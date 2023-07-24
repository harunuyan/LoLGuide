package com.volie.lolguidestats.ui.fragment.summoner_spell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.summoner_spell.SummonerSpell
import com.volie.lolguidestats.databinding.AdapterItemSummonerSpellBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class SummonerSpellRVAdapter : BaseAdapter<SummonerSpell>(SummonerSpellDiffCallback()) {

    inner class SummonerSpellViewHolder(private val binding: AdapterItemSummonerSpellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val spell = currentList[position]

            with(binding) {
                Glide.with(root)
                    .load("${BASE_URL}img/spell/${spell.id}.png")
                    .into(ivItemImage)

                tvItemName.text = spell.name
                tvItemPlain.text = spell.description
                tvLevel.text = "Level ${spell.summonerLevel}"
                tvItemMode.text = spell.modes.toString().trim('[', ']')
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = AdapterItemSummonerSpellBinding.inflate(inflater, parent, false)
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
