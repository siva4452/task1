package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TaskAdapter(context: Context, tasks: List<Task>) : ArrayAdapter<Task>(context, 0, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val task = getItem(position)

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        }

        // Lookup view for data population
        val textViewTask = convertView!!.findViewById<TextView>(android.R.id.text1)

        // Populate the data into the template view using the data object
        task?.let {
            textViewTask.text = it.taskName
        }

        // Return the completed view to render on screen
        return convertView
    }
}
