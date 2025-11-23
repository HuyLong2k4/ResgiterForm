package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Dữ liệu mẫu
        val suggestedApps = listOf(
            AppItem(1, "Mech Assemble: Zombie", "Action • Roguelike", ""),
            AppItem(2, "MU: Hỏa Long Đao", "Role Playing", ""),
            AppItem(3, "War Inc: Rising", "Strategy", ""),
        )

        val recommendedApps = listOf(
            AppItem(6, "Suno AI", "Music", "#FF5722"), // Cam
            AppItem(7, "Claude", "AI Chat", "#D7CCC8"), // Nâu
            AppItem(8, "DramaBox", "Movies", "#E91E63"),
            AppItem(8, "DramaBox", "Movies", "#E91E63")// Hồng
        )

        // 2. Tạo danh sách Section với Type tương ứng
        val sections = listOf(
            // Mục 1: Dọc (TYPE_VERTICAL) -> Sẽ dùng layout 3 dòng
            SectionItem("Sponsored • Suggested for you", suggestedApps, SectionItem.TYPE_VERTICAL),

            // Mục 2: Ngang (TYPE_HORIZONTAL) -> Sẽ dùng layout ô vuông
            SectionItem("Recommended for you", recommendedApps, SectionItem.TYPE_HORIZONTAL)
        )

        // 3. Setup RecyclerView Chính
        val rvMain = findViewById<RecyclerView>(R.id.rvMain)
        rvMain.layoutManager = LinearLayoutManager(this) // Cuộn dọc toàn màn hình
        rvMain.adapter = SectionAdapter(sections)
    }
}