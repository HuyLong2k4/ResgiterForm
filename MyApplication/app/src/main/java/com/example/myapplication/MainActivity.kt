package com.example.myapplication // Thay thế bằng tên package của bạn

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Khai báo các View (Đối tượng giao diện)
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var editTextBirthday: EditText
    private lateinit var buttonSelectDate: Button
    private lateinit var calendarView: CalendarView
    private lateinit var editTextAddress: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var checkBoxTerms: CheckBox
    private lateinit var buttonRegister: Button

    // Biến để lưu màu nền mặc định
    private var defaultEditTextBackground: Drawable? = null
    private var defaultLabelColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các View
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        editTextBirthday = findViewById(R.id.editTextBirthday)
        buttonSelectDate = findViewById(R.id.buttonSelectDate)
        calendarView = findViewById(R.id.calendarView)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextEmail = findViewById(R.id.editTextEmail)
        checkBoxTerms = findViewById(R.id.checkBoxTerms)
        buttonRegister = findViewById(R.id.buttonRegister)

        // Lưu lại màu nền mặc định để reset khi hợp lệ
        defaultEditTextBackground = editTextFirstName.background
        defaultLabelColor = checkBoxTerms.textColors.defaultColor

        // --- Xử lý Yêu cầu 1: Ẩn/hiện CalendarView ---
        buttonSelectDate.setOnClickListener {
            if (calendarView.visibility == View.GONE) {
                calendarView.visibility = View.VISIBLE
            } else {
                calendarView.visibility = View.GONE
            }
        }

        // Xử lý khi người dùng chọn ngày trên CalendarView
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // (month bắt đầu từ 0, nên cần +1)
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            editTextBirthday.setText(selectedDate)
            calendarView.visibility = View.GONE // Tự động ẩn sau khi chọn
        }

        // --- Xử lý Yêu cầu 2: Kiểm tra khi nhấn Register ---
        buttonRegister.setOnClickListener {
            if (validateForm()) {
                // Nếu tất cả hợp lệ
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
            } else {
                // Nếu có lỗi
                Toast.makeText(this, "Please fill all required fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Hàm kiểm tra toàn bộ form.
     * Trả về true nếu hợp lệ, false nếu có lỗi.
     * Sẽ đổi màu nền các trường bị lỗi thành màu đỏ.
     */
    private fun validateForm(): Boolean {
        var isValid = true

        // 1. Kiểm tra First Name
        if (editTextFirstName.text.isBlank()) {
            editTextFirstName.setBackgroundColor(Color.RED)
            isValid = false
        } else {
            editTextFirstName.background = defaultEditTextBackground
        }

        // 2. Kiểm tra Last Name
        if (editTextLastName.text.isBlank()) {
            editTextLastName.setBackgroundColor(Color.RED)
            isValid = false
        } else {
            editTextLastName.background = defaultEditTextBackground
        }

        // 3. Kiểm tra Gender
        val genderLabel = findViewById<TextView>(R.id.textViewGenderLabel)
        if (radioGroupGender.checkedRadioButtonId == -1) {
            // Đổi màu chữ của label "Gender"
            genderLabel.setTextColor(Color.RED)
            isValid = false
        } else {
            genderLabel.setTextColor(defaultLabelColor)
        }

        // 4. Kiểm tra Birthday
        if (editTextBirthday.text.isBlank()) {
            editTextBirthday.setBackgroundColor(Color.RED)
            isValid = false
        } else {
            editTextBirthday.background = defaultEditTextBackground
        }

        // 5. Kiểm tra Address
        if (editTextAddress.text.isBlank()) {
            editTextAddress.setBackgroundColor(Color.RED)
            isValid = false
        } else {
            editTextAddress.background = defaultEditTextBackground
        }

        // 6. Kiểm tra Email
        if (editTextEmail.text.isBlank()) {
            editTextEmail.setBackgroundColor(Color.RED)
            isValid = false
        } else {
            editTextEmail.background = defaultEditTextBackground
        }

        // 7. Kiểm tra Terms of Use
        if (!checkBoxTerms.isChecked) {
            // Đổi màu chữ của CheckBox
            checkBoxTerms.setTextColor(Color.RED)
            isValid = false
        } else {
            checkBoxTerms.setTextColor(defaultLabelColor)
        }

        return isValid
    }
}