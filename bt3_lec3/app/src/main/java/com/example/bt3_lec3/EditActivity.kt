package com.example.bt3_lec3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bt3_lec3.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Nhận tên hiện tại từ MainActivity và hiển thị sẵn
        val currentName = intent.getStringExtra(MainActivity.EXTRA_USER_NAME)
        if (!currentName.isNullOrEmpty()) {
            binding.edtName.setText(currentName)
            // Di chuyển con trỏ soạn thảo về cuối dòng chữ
            binding.edtName.setSelection(currentName.length)
        }

        // 2. Xử lý nút bấm "Lưu & Quay lại"
        binding.btnSaveAndBack.setOnClickListener {
            val newName = binding.edtName.text.toString().trim()

            if (newName.isEmpty()) {
                Toast.makeText(this, "Vui lòng không để trống họ tên", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Đóng gói tên mới vào Intent trả về
            val returnIntent = Intent().apply {
                putExtra(MainActivity.EXTRA_USER_NAME, newName)
            }
            setResult(Activity.RESULT_OK, returnIntent)

            // Đóng màn hình hiện tại để quay về MainActivity
            finish()
        }
    }
}