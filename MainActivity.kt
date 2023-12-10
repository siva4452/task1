package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.example.todolist.R
import com.example.todolist.Task
import com.example.todolist.TaskAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var editTextTask: EditText
    private lateinit var buttonAdd: Button
    private lateinit var listViewTasks: ListView
    private lateinit var taskList: ArrayList<Task>
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        buttonAdd = findViewById(R.id.buttonAdd)
        listViewTasks = findViewById(R.id.listViewTasks)

        taskList = ArrayList()
        taskAdapter = TaskAdapter(this, taskList)
        listViewTasks.adapter = taskAdapter

        buttonAdd.setOnClickListener {
            addTask()
        }

        // Set a long click listener on the ListView items to prompt for deletion
        listViewTasks.setOnItemLongClickListener { _, _, position, _ ->
            showDeleteDialog(position)
            true
        }
    }

    private fun addTask() {
        val taskName = editTextTask.text.toString().trim()

        if (taskName.isNotEmpty()) {
            val task = Task(taskName)
            taskList.add(task)
            taskAdapter.notifyDataSetChanged()
            editTextTask.text.clear()
        }
    }

    private fun showDeleteDialog(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Task")
        builder.setMessage("Are you sure you want to delete this task?")
        builder.setPositiveButton("Delete") { _, _ ->
            deleteTask(position)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteTask(position: Int) {
        taskList.removeAt(position)
        taskAdapter.notifyDataSetChanged()
    }
}
