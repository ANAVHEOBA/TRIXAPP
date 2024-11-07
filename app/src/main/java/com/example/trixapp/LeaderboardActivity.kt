package com.example.trixapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trixapp.adapter.LeaderboardAdapter
import com.example.trixapp.databinding.ActivityLeaderboardBinding
import com.example.trixapp.model.LeaderboardItem

class LeaderboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderboardBinding
    private lateinit var leaderboardAdapter: LeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_leaderboard)

        setupUI()
        setupRecyclerView()
        loadLeaderboardData()
    }

    private fun setupUI() {
        window.statusBarColor = getColor(R.color.purple_700)

        binding.apply {
            menuButton.setOnClickListener {
                showMenu()
            }

            statsButton.setOnClickListener {
                // Handle stats click
            }
        }
    }

    private fun setupRecyclerView() {
        leaderboardAdapter = LeaderboardAdapter()
        binding.leaderboardRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@LeaderboardActivity)
            adapter = leaderboardAdapter
        }
    }

    private fun loadLeaderboardData() {
        // Simulate data loading
        val leaderboardItems = listOf(
            LeaderboardItem(1, "John Doe", 300, true),
            LeaderboardItem(2, "John Doe", 290, true),
            LeaderboardItem(3, "Jane Doe", 280, false),
            LeaderboardItem(4, "John Doe", 270, false),
            LeaderboardItem(5, "Jane Doe", 260, false),
            LeaderboardItem(6, "John Doe", 250, false),
            LeaderboardItem(7, "John Doe", 240, true),
            LeaderboardItem(8, "Jane Doe", 230, false),
            LeaderboardItem(9, "Jane Doe", 220, true),
            LeaderboardItem(10, "You", 110, true)
        )
        leaderboardAdapter.submitList(leaderboardItems)
    }
}