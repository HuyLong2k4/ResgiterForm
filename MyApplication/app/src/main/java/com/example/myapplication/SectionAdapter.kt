package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Cập nhật Model Section để thêm loại hiển thị (Type)
data class SectionItem(
    val title: String,
    val apps: List<AppItem>,
    val type: Int // 0: Dọc (Suggested), 1: Ngang (Recommended)
) {
    companion object {
        const val TYPE_VERTICAL = 0
        const val TYPE_HORIZONTAL = 1
    }
}

class SectionAdapter(private val sections: List<SectionItem>) :
    RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvSectionTitle)
        val rvInner: RecyclerView = view.findViewById(R.id.rvHorizontalApps) // Dùng lại ID trong item_section.xml
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_section, parent, false) // layout item_section chứa Title + RecyclerView rỗng
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]
        holder.tvTitle.text = section.title

        // --- CẤU HÌNH RECYCLERVIEW CON DỰA TRÊN TYPE ---
        if (section.type == SectionItem.TYPE_VERTICAL) {
            // Cấu hình cho SUGGESTED (Dọc)
            holder.rvInner.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)

            // QUAN TRỌNG: Dùng layout 3 dòng (item_suggested_app)
            holder.rvInner.adapter = GenericAppAdapter(section.apps, R.layout.item_suggested_app)

            // KỸ THUẬT: Tắt cuộn của RV con để RV cha quản lý việc cuộn -> Mượt mà
            holder.rvInner.isNestedScrollingEnabled = false

        } else {
            // Cấu hình cho RECOMMENDED (Ngang)
            holder.rvInner.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

            // QUAN TRỌNG: Dùng layout ô vuông (item_recommended_card)
            holder.rvInner.adapter = GenericAppAdapter(section.apps, R.layout.item_recommended_card)

            // RV ngang thì vẫn cần cuộn ngang
            holder.rvInner.isNestedScrollingEnabled = true
        }
    }

    override fun getItemCount() = sections.size
}