package com.example.trixapp

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.trixapp.databinding.ActivityWelcomeBinding
import com.google.android.material.card.MaterialCardView

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private var isConnected = true
    private val userRank = "#20,056"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        
        setupStatusBar()
        setupUI()
        animateContent()
    }

    private fun setupStatusBar() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_700)
    }

    private fun setupUI() {
        // Set user information
        binding.apply {
            welcomeText.text = getString(R.string.welcome_message, "John Doe")
            rankText.text = getString(R.string.rank_message, userRank)
            
            // Setup connection status
            connectionStatus.apply {
                statusDot.setBackgroundResource(
                    if (isConnected) R.drawable.connected_dot 
                    else R.drawable.disconnected_dot
                )
                statusText.text = if (isConnected) "Connected" else "Disconnected"
                connectionId.text = "0001123412462"
            }

            // Setup click listeners
            menuButton.setOnClickListener { 
                // Handle menu click
            }
            
            statsButton.setOnClickListener {
                // Handle stats click
            }
        }
    }

    private fun animateContent() {
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        binding.apply {
            welcomeContainer.startAnimation(fadeIn)
            rankCard.startAnimation(slideUp)
            connectionCard.startAnimation(slideUp)
        }
    }
}