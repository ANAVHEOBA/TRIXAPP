package com.example.trixapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trixapp.databinding.ActivityRegistrationBinding
import com.google.android.material.textfield.TextInputLayout

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        // Set status bar color
        window.statusBarColor = getColor(R.color.purple_700)
    }

    private fun setupListeners() {
        binding.apply {
            // Get Started button click listener
            getStartedButton.setOnClickListener {
                if (validateInputs()) {
                    // Navigate to Welcome Activity
                    startActivity(Intent(this@RegistrationActivity, WelcomeActivity::class.java))
                    finish()
                }
            }

            // Menu button click listener
            menuButton.setOnClickListener {
                // Handle menu click
            }

            // Stats button click listener
            statsButton.setOnClickListener {
                // Handle stats click
            }
        }
    }

    private fun validateInputs(): Boolean {
        binding.apply {
            val isNameValid = validateField(fullNameLayout, fullNameEdit.text.toString())
            val isEmailValid = validateField(emailLayout, emailEdit.text.toString())
            val isPhoneValid = validateField(phoneLayout, phoneEdit.text.toString())

            return isNameValid && isEmailValid && isPhoneValid
        }
    }

    private fun validateField(layout: TextInputLayout, text: String): Boolean {
        return if (text.isEmpty()) {
            layout.error = "This field is required"
            false
        } else {
            layout.error = null
            true
        }
    }
}