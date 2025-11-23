package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter này nhận vào một layoutId để biết cần vẽ giao diện nào (Suggested hay Recommended)
class GenericAppAdapter(
    private val apps: List<AppItem>,
    private val layoutId: Int
) : RecyclerView.Adapter<GenericAppAdapter.AppViewHolder>() {

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Khai báo chung các ID có thể xuất hiện
        val tvName: TextView? = view.findViewById(R.id.tvAppName) // ID này có ở layout Suggested
        val tvRecName: TextView? = view.findViewById(R.id.tvRecName) // ID này có ở layout Recommended

        val tvDesc: TextView? = view.findViewById(R.id.tvDesc) // Layout Suggested
        val tvRating: TextView? = view.findViewById(R.id.tvRating) // Layout Suggested

        val imgIcon: ImageView? = view.findViewById(R.id.imgIcon) // Layout Suggested
        val imgRec: ImageView? = view.findViewById(R.id.imgRec) // Layout Recommended
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]

        // Logic điền dữ liệu (kiểm tra xem view có tồn tại không trước khi set)

        // 1. Nếu là Layout SUGGESTED (3 dòng)
        holder.tvName?.text = app.name
        holder.tvDesc?.text = app.category
        holder.tvRating?.text = "4.5 ★   100 MB"
        holder.imgIcon?.setImageResource(R.mipmap.ic_launcher) // Hoặc set màu: holder.imgIcon?.setBackgroundColor(...)

        // 2. Nếu là Layout RECOMMENDED (Ô vuông)
        holder.tvRecName?.text = app.name
        try {
            holder.imgRec?.setBackgroundColor(Color.parseColor(app.imageColor))
        } catch (e: Exception) {
            holder.imgRec?.setBackgroundColor(Color.LTGRAY)
        }
    }

    override fun getItemCount() = apps.size
}