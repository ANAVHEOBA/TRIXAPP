package com.example.trixapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trixapp.databinding.ActivityConnectWalletBinding
import com.example.trixapp.dialog.MenuDialog

class ConnectWalletActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConnectWalletBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_connect_wallet)

        setupUI()
        setupListeners()
        animateContent()
    }

    private fun setupUI() {
        window.statusBarColor = getColor(R.color.purple_700)
        
        binding.apply {
            welcomeText.text = getString(R.string.welcome_message, "John Doe")
            rankText.text = getString(R.string.rank_message, "#20,056")
            walletId.text = "0001123412462"
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

            floatingIcons.forEach { icon ->
                icon.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@ConnectWalletActivity, 
                        R.anim.float_animation
                    )
                )
            }
        }
    }

    private fun showMenu() {
        MenuDialog(
            context = this,
            onHomeClick = {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            },
            onTasksClick = {
                startActivity(Intent(this, TasksActivity::class.java))
                finish()
            },
            onReferralsClick = {
                startActivity(Intent(this, ReferralsActivity::class.java))
                finish()
            },
            onAboutClick = {
                startActivity(Intent(this, AboutActivity::class.java))
                finish()
            }
        ).show()
    }

    private fun animateContent() {
        binding.apply {
            val fadeIn = AnimationUtils.loadAnimation(
                this@ConnectWalletActivity, 
                R.anim.fade_in
            )
            val slideUp = AnimationUtils.loadAnimation(
                this@ConnectWalletActivity, 
                R.anim.slide_up
            )

            welcomeContainer.startAnimation(fadeIn)
            rankContainer.startAnimation(slideUp)
            walletContainer.startAnimation(slideUp)
        }
    }
}