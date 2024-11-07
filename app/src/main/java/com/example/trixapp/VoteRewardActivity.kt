package com.example.trixapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trixapp.databinding.ActivityVoteRewardBinding
import com.example.trixapp.dialog.WaitlistSuccessDialog

class VoteRewardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoteRewardBinding
    private var currentVotePercentage = 50 // Default 50-50 split
    private var hasVoted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vote_reward)

        setupUI()
        setupListeners()
        updateVoterAvatars()
    }

    private fun setupUI() {
        window.statusBarColor = getColor(R.color.purple_700)
        updateProgressBar()
    }

    private fun setupListeners() {
        binding.apply {
            menuButton.setOnClickListener {
                // Handle menu click
            }

            statsButton.setOnClickListener {
                // Handle stats click
            }

            leftVoteArea.setOnClickListener {
                if (!hasVoted) {
                    currentVotePercentage = maxOf(currentVotePercentage - 10, 0)
                    updateProgressBar()
                    handleVote()
                }
            }

            rightVoteArea.setOnClickListener {
                if (!hasVoted) {
                    currentVotePercentage = minOf(currentVotePercentage + 10, 100)
                    updateProgressBar()
                    handleVote()
                }
            }
        }
    }

    private fun handleVote() {
        hasVoted = true
        // Show success dialog after a short delay
        binding.root.postDelayed({
            showSuccessDialog()
        }, 500) // 500ms delay for better UX
    }

    private fun updateProgressBar() {
        binding.apply {
            progressBar.progress = currentVotePercentage
            leftPercentage.text = "${currentVotePercentage}%"
            rightPercentage.text = "${100 - currentVotePercentage}%"
        }
    }

    private fun updateVoterAvatars() {
        // Update the voter avatars display
        // This would typically be updated based on real data
    }

    private fun showSuccessDialog() {
        val dialog = WaitlistSuccessDialog(
            context = this,
            referralCode = generateReferralCode(),
            onDashboardClick = {
                navigateToDashboard()
            }
        )
        dialog.show()
    }

    private fun generateReferralCode(): String {
        // In a real app, this would come from your backend
        return "TRIX${(1000..9999).random()}"
    }

    private fun navigateToDashboard() {
        // Navigate to dashboard
        // Example:
        // startActivity(Intent(this, DashboardActivity::class.java))
        // finish()
    }
}