package com.volie.lolguidestats.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.champion.Champion
import com.volie.lolguidestats.databinding.AdapterItemChampionHomeBinding
import com.volie.lolguidestats.helper.Constant
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class ChampRVAdapter(
    private val listener: (Champion) -> Unit
) : BaseAdapter<Champion>(ChampionHomeDiffUtilCallback()) {

    inner class ChampionHomeViewHolder(private val binding: AdapterItemChampionHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val champ = currentList[position]
            Glide.with(binding.root)
                .load("${Constant.BASE_URL}img/champion/${champ.image?.full}")
                .into(binding.ivChampImageItem)

            binding.tvChampNameItem.text = champ.name

            binding.root.setOnClickListener {
                listener(champ)
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = AdapterItemChampionHomeBinding.inflate(inflater, parent, false)
        return ChampionHomeViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Champion, position: Int) {
        if (holder is ChampionHomeViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class ChampionHomeDiffUtilCallback : DiffUtil.ItemCallback<Champion>() {
    override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem == newItem
    }
}
