package com.volie.lolguidestats.ui.fragment.mode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.lolguidestats.data.model.mode.Mode
import com.volie.lolguidestats.databinding.ItemGameModeBinding
import com.volie.lolguidestats.ui.adapter.BaseAdapter

class GameModeRVAdapter(
    private val onItemClicked: (Mode) -> Unit
) : BaseAdapter<Mode>(ModeDiffUtilCallback()) {

    inner class ModeViewHolder(private val binding: ItemGameModeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val mode = currentList[position]

            with(binding) {
                Glide.with(root)
                    .load(mode.url)
                    .into(ivGameMode)

                tvGameMode.text = mode.gameMode

                root.setOnClickListener {
                    onItemClicked(mode)
                }
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemGameModeBinding.inflate(inflater, parent, false)
        return ModeViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Mode, position: Int) {
        if (holder is ModeViewHolder) {
            holder.bind(position)
        }
    }


    override fun getItemCount(): Int {
        return currentList.size
    }
}

class ModeDiffUtilCallback : DiffUtil.ItemCallback<Mode>() {
    override fun areItemsTheSame(oldItem: Mode, newItem: Mode): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Mode, newItem: Mode): Boolean {
        return oldItem == newItem
    }
}
