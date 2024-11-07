package com.example.trixapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trixapp.adapter.TasksAdapter
import com.example.trixapp.databinding.ActivityTasksBinding
import com.example.trixapp.dialog.MenuDialog
import com.example.trixapp.model.Task
import com.example.trixapp.model.TaskType
import com.google.android.material.tabs.TabLayout

class TasksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTasksBinding
    private lateinit var tasksAdapter: TasksAdapter
    private var currentTaskType = TaskType.ONE_TIME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)

        setupUI()
        setupTabs()
        setupRecyclerView()
        loadTasks(TaskType.ONE_TIME)
    }

    private fun setupUI() {
        window.statusBarColor = getColor(R.color.purple_700)

        binding.apply {
            menuButton.setOnClickListener {
                showMenu()
            }

            statsButton.setOnClickListener {
                // Handle stats click
            }
        }
    }

    private fun showMenu() {
        MenuDialog(
            context = this,
            onHomeClick = {
                finish()
            },
            onTasksClick = {
                // Already on Tasks
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

    private fun setupTabs() {
        binding.tabLayout.apply {
            addTab(newTab().setText(R.string.tab_one_time))
            addTab(newTab().setText(R.string.tab_daily))
            addTab(newTab().setText(R.string.tab_completed))
            
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    currentTaskType = when (tab?.position) {
                        0 -> TaskType.ONE_TIME
                        1 -> TaskType.DAILY
                        2 -> TaskType.COMPLETED
                        else -> TaskType.ONE_TIME
                    }
                    loadTasks(currentTaskType)
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun setupRecyclerView() {
        tasksAdapter = TasksAdapter { task ->
            // Handle task click
            // You might want to show a completion dialog or navigate to task details
        }
        
        binding.tasksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TasksActivity)
            adapter = tasksAdapter
            setHasFixedSize(true)
        }
    }

    private fun loadTasks(type: TaskType) {
        val tasks = when (type) {
            TaskType.ONE_TIME -> listOf(
                Task(
                    "Connect Crypto-wallet",
                    "Connect your Crypto-wallet and earn 10 point getting you closer to your desired reward.",
                    "1/1",
                    10,
                    true
                ),
                Task(
                    "Share on Social Media",
                    "Share post on your socials and earn 50 point getting you closer to your desired reward.",
                    "0/1",
                    50,
                    false
                ),
                Task(
                    "Share on Social Media",
                    "Share post on your socials and earn 50 point getting you closer to your desired reward.",
                    "0/1",
                    50,
                    false
                ),
                Task(
                    "Share on Social Media",
                    "Share post on your socials and earn 50 point getting you closer to your desired reward.",
                    "0/1",
                    50,
                    false
                )
            )
            
            TaskType.DAILY -> listOf(
                Task(
                    "Share on Social Media",
                    "Share post on your socials and earn 50 point getting you closer to your desired reward.",
                    "0/5",
                    50,
                    false
                ),
                Task(
                    "Share on Social Media",
                    "Share post on your socials and earn 50 point getting you closer to your desired reward.",
                    "0/5",
                    50,
                    false
                ),
                Task(
                    "Share on Social Media",
                    "Share post on your socials and earn 50 point getting you closer to your desired reward.",
                    "0/5",
                    50,
                    false
                ),
                Task(
                    "Share on Social Media",
                    "Share post on your socials and earn 50 point getting you closer to your desired reward.",
                    "0/5",
                    50,
                    false
                )
            )
            
            TaskType.COMPLETED -> listOf(
                Task(
                    "Connect Crypto-wallet",
                    "Connect your Crypto-wallet and earn 10 point getting you closer to your desired reward.",
                    "1/1",
                    10,
                    true
                )
            )
        }
        
        tasksAdapter.submitList(tasks)
        updateEmptyState(tasks.isEmpty())
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.apply {
            if (isEmpty) {
                emptyStateLayout.visibility = View.VISIBLE
                tasksRecyclerView.visibility = View.GONE
                
                when (currentTaskType) {
                    TaskType.COMPLETED -> {
                        emptyStateText.text = getString(R.string.no_completed_tasks)
                    }
                    else -> {
                        emptyStateText.text = getString(R.string.no_tasks_available)
                    }
                }
            } else {
                emptyStateLayout.visibility = View.GONE
                tasksRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh tasks when returning to the screen
        loadTasks(currentTaskType)
    }
}