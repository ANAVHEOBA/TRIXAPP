package com.example.trixapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trixapp.R
import com.example.trixapp.databinding.ItemLeaderboardBinding
import com.example.trixapp.model.LeaderboardItem

class LeaderboardAdapter : ListAdapter<LeaderboardItem, LeaderboardAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLeaderboardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemLeaderboardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LeaderboardItem) {
            binding.apply {
                rankText.text = "#${item.rank}"
                nameText.text = item.name
                pointsText.text = "${item.points}pts"
                
                trendIcon.setImageResource(
                    if (item.isIncreasing) R.drawable.ic_trend_up 
                    else R.drawable.ic_trend_down
                )

                // Highlight current user
                if (item.name == "You") {
                    root.setBackgroundResource(R.drawable.bg_leaderboard_current_user)
                } else {
                    root.setBackgroundResource(android.R.color.transparent)
                }
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<LeaderboardItem>() {
        override fun areItemsTheSame(oldItem: LeaderboardItem, newItem: LeaderboardItem) =
            oldItem.rank == newItem.rank

        override fun areContentsTheSame(oldItem: LeaderboardItem, newItem: LeaderboardItem) =
            oldItem == newItem
    }
}