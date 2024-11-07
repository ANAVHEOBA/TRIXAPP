package com.example.trixapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trixapp.databinding.ActivityHomeBinding
import com.example.trixapp.dialog.MenuDialog

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var currentVotePercentage = 50 // Default 50-50 split
    private var isConnected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setupUI()
        setupListeners()
        animateContent()
    }

    private fun setupUI() {
        window.statusBarColor = getColor(R.color.purple_700)
        
        binding.apply {
            // Set user info
            welcomeText.text = getString(R.string.welcome_message, "John Doe")
            rankText.text = getString(R.string.rank_message, "#20,056")

            // Set connection status
            updateConnectionStatus()
        }
    }

    private fun setupListeners() {
        binding.apply {
            menuButton.setOnClickListener {
                showMenu()
            }

            statsButton.setOnClickListener {
                // Handle stats click
            }

            connectButton.setOnClickListener {
                isConnected = !isConnected
                updateConnectionStatus()
            }

            leftVoteArea.setOnClickListener {
                currentVotePercentage = maxOf(currentVotePercentage - 10, 0)
                updateProgressBar()
            }

            rightVoteArea.setOnClickListener {
                currentVotePercentage = minOf(currentVotePercentage + 10, 100)
                updateProgressBar()
            }
        }
    }

    private fun showMenu() {
        MenuDialog(
            context = this,
            onHomeClick = {
                // Already on Home
            },
            onTasksClick = {
                startActivity(Intent(this, TasksActivity::class.java))
            },
            onReferralsClick = {
                startActivity(Intent(this, ReferralsActivity::class.java))
            },
            onAboutClick = {
                startActivity(Intent(this, AboutActivity::class.java))
            }
        ).show()
    }

    private fun updateConnectionStatus() {
        binding.apply {
            connectionStatus.text = if (isConnected) 
                getString(R.string.connected) 
            else 
                getString(R.string.disconnected)
            connectionId.text = "0001123412462"
            connectButton.text = if (isConnected) 
                getString(R.string.connected) 
            else 
                getString(R.string.disconnected)
            statusIndicator.setBackgroundResource(
                if (isConnected) R.drawable.connected_dot 
                else R.drawable.disconnected_dot
            )
        }
    }

    private fun updateProgressBar() {
        binding.apply {
            progressBar.progress = currentVotePercentage
            leftPercentage.text = "${currentVotePercentage}%"
            rightPercentage.text = "${100 - currentVotePercentage}%"
        }
    }

    private fun animateContent() {
        binding.apply {
            val fadeIn = AnimationUtils.loadAnimation(this@HomeActivity, R.anim.fade_in)
            val slideUp = AnimationUtils.loadAnimation(this@HomeActivity, R.anim.slide_up)

            // Animate welcome section
            welcomeContainer.startAnimation(fadeIn)
            
            // Animate rank and connection sections
            rankContainer.startAnimation(slideUp)
            connectionContainer.startAnimation(slideUp)
        }
    }
}