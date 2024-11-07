package com.example.trixapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trixapp.databinding.ActivitySocialShareTaskBinding

class SocialShareTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySocialShareTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_social_share_task)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        window.statusBarColor = getColor(R.color.purple_700)
    }

    private fun setupListeners() {
        binding.apply {
            menuButton.setOnClickListener {
                showMenu()
            }

            statsButton.setOnClickListener {
                // Handle stats click
            }

            // Social media buttons
            facebookButton.setOnClickListener { openSocialMedia("facebook") }
            twitterButton.setOnClickListener { openSocialMedia("twitter") }
            instagramButton.setOnClickListener { openSocialMedia("instagram") }
            discordButton.setOnClickListener { openSocialMedia("discord") }
        }
    }

    private fun openSocialMedia(platform: String) {
        val url = when (platform) {
            "facebook" -> "https://facebook.com/trixreward"
            "twitter" -> "https://twitter.com/trixreward"
            "instagram" -> "https://instagram.com/trixreward"
            "discord" -> "https://discord.gg/trixreward"
            else -> return
        }

        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            // Handle error (maybe show a toast)
        }
    }
}