package com.example.trixapp.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.example.trixapp.databinding.DialogMenuBinding

class MenuDialog(
    context: Context,
    private val onHomeClick: () -> Unit,
    private val onTasksClick: () -> Unit,
    private val onReferralsClick: () -> Unit,
    private val onAboutClick: () -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDialog()
        setupClickListeners()
    }

    private fun setupDialog() {
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            setGravity(Gravity.START)
            
            // Add slide animation
            attributes.windowAnimations = android.R.style.Animation_Dialog
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            closeButton.setOnClickListener { dismiss() }
            
            homeOption.setOnClickListener {
                onHomeClick()
                dismiss()
            }
            
            tasksOption.setOnClickListener {
                onTasksClick()
                dismiss()
            }
            
            referralsOption.setOnClickListener {
                onReferralsClick()
                dismiss()
            }
            
            aboutOption.setOnClickListener {
                onAboutClick()
                dismiss()
            }
        }
    }
}