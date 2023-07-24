package com.volie.lolguidestats.ui.fragment.champion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.champion.Champion
import com.volie.lolguidestats.databinding.AdapterItemChampionBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL

class ChampRVAdapter(
    val onChampClick: (Champion) -> Unit
) : ListAdapter<Champion, ChampRVAdapter.ChampViewHolder>(
    ChampDiffCallback()
) {
    inner class ChampViewHolder(private val binding: AdapterItemChampionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val champ = currentList[position]
            Glide.with(binding.root)
                .load("${BASE_URL}img/champion/${champ.image?.full}")
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
    ): ChampViewHolder {
        val binding =
            AdapterItemChampionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChampViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampViewHolder, position: Int) {
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
