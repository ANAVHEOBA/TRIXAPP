package com.example.trixapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trixapp.databinding.ActivityCategorySelectionBinding

class CategorySelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategorySelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_selection)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        window.statusBarColor = getColor(R.color.purple_700)
    }

    private fun setupListeners() {
        binding.apply {
            // Menu button click listener
            menuButton.setOnClickListener {
                // Handle menu click
            }

            // Stats button click listener
            statsButton.setOnClickListener {
                // Handle stats click
            }

            // Category selection listeners
            rewardSeekerCard.setOnClickListener {
                handleCategorySelection("Reward Seeker")
            }

            gamerTechCard.setOnClickListener {
                handleCategorySelection("Gamer/Tech Enthusiast")
            }

            casualShopperCard.setOnClickListener {
                handleCategorySelection("Casual Shopper")
            }

            othersCard.setOnClickListener {
                handleCategorySelection("Others")
            }
        }
    }

    private fun handleCategorySelection(category: String) {
        // Save category selection
        // Navigate to next screen (Welcome or Registration)
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}