package com.volie.lolguidestats.ui.fragment.rank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.rank.Season
import com.volie.lolguidestats.databinding.ItemSeasonBinding
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class RankRVAdapter(
    private val onItemClickListener: ((Season) -> Unit)
) : BaseAdapter<Season>(SeasonDiffUtilCallback()) {

    inner class SeasonViewHolder(private val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val rank = currentList[position]
            binding.tvSeason.text = rank.name

            Glide.with(binding.root)
                .load(rank.image)
                .into(binding.ivSeason)

            binding.root.setOnClickListener {
                onItemClickListener(rank)
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemSeasonBinding.inflate(inflater, parent, false)
        return SeasonViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Season, position: Int) {
        if (holder is SeasonViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class SeasonDiffUtilCallback : DiffUtil.ItemCallback<Season>() {
    override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean {
        return oldItem == newItem
    }
}
