package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(private val apps: List<AppItem>) :
    RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvAppName)
        val tvCat: TextView = view.findViewById(R.id.tvAppCat)
        val img: ImageView = view.findViewById(R.id.imgApp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app_card, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.tvName.text = app.name
        holder.tvCat.text = app.category
        // Giả lập load màu ảnh
        try {
            holder.img.setBackgroundColor(Color.parseColor(app.imageColor))
        } catch (e: Exception) {
            holder.img.setBackgroundColor(Color.LTGRAY)
        }
    }

    override fun getItemCount() = apps.size
}