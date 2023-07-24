package com.volie.lolguidestats.ui.fragment.rank_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.rank.Rank
import com.volie.lolguidestats.databinding.AdapterItemRankBinding
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class RankDetailsRVAdapter : BaseAdapter<Rank>(RankDiffUtilCallback()) {

    inner class RankViewHolder(private val binding: AdapterItemRankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val rank = currentList[position]
            Glide.with(binding.root)
                .load(rank.url)
                .into(binding.ivRank)

            binding.tvRank.text = rank.rankName
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = AdapterItemRankBinding.inflate(inflater, parent, false)
        return RankViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Rank, position: Int) {
        if (holder is RankViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class RankDiffUtilCallback : DiffUtil.ItemCallback<Rank>() {
    override fun areItemsTheSame(oldItem: Rank, newItem: Rank): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Rank, newItem: Rank): Boolean {
        return oldItem == newItem
    }
}
