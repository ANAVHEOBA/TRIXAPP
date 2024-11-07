package com.example.trixapp.dialog

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.trixapp.databinding.DialogWaitlistSuccessBinding

class WaitlistSuccessDialog(
    context: Context,
    private val referralCode: String,
    private val onDashboardClick: () -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogWaitlistSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogWaitlistSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set dialog properties
        window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setLayout(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        binding.referralCodeText.text = referralCode
    }

    private fun setupListeners() {
        binding.apply {
            // Share code button click
            shareCodeButton.setOnClickListener {
                copyToClipboard(referralCode)
            }

            // Dashboard button click
            gotoDashboardButton.setOnClickListener {
                dismiss()
                onDashboardClick()
            }
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(context, ClipboardManager::class.java)
        val clip = ClipData.newPlainText("Referral Code", text)
        clipboard?.setPrimaryClip(clip)
        Toast.makeText(context, "Code copied to clipboard!", Toast.LENGTH_SHORT).show()
    }
}