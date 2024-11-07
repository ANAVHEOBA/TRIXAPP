package com.example.trixapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trixapp.databinding.ActivityTaskDetailsBinding

class TaskDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskDetailsBinding
    private val inviteCode = "TRIXOFF15H" // Example code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_details)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        window.statusBarColor = getColor(R.color.purple_700)
        binding.inviteCodeText.text = inviteCode
    }

    private fun setupListeners() {
        binding.apply {
            menuButton.setOnClickListener {
                showMenu()
            }

            statsButton.setOnClickListener {
                // Handle stats click
            }

            shareCodeButton.setOnClickListener {
                copyToClipboard(inviteCode)
            }

            // Social media share buttons
            facebookButton.setOnClickListener { shareToSocial("facebook") }
            twitterButton.setOnClickListener { shareToSocial("twitter") }
            instagramButton.setOnClickListener { shareToSocial("instagram") }
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Invite Code", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Code copied to clipboard!", Toast.LENGTH_SHORT).show()
    }

    private fun shareToSocial(platform: String) {
        val shareText = "Join me on TrixSmart! Use my invite code: $inviteCode"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}