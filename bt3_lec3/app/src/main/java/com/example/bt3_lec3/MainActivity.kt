package com.example.bt3_lec3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.bt3_lec3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Khai báo launcher để lắng nghe kết quả trả về từ EditActivity
    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newName = result.data?.getStringExtra(EXTRA_USER_NAME)
            if (!newName.isNullOrBlank()) {
                binding.tvCurrentName.text = newName
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEditInfo.setOnClickListener {
            val currentName = binding.tvCurrentName.text.toString()

            // Gửi họ tên hiện tại sang EditActivity
            val intent = Intent(this, EditActivity::class.java).apply {
                // Nếu đang là chữ mặc định thì không truyền sang để tránh phải xóa tay
                if (currentName != "Chưa có thông tin") {
                    putExtra(EXTRA_USER_NAME, currentName)
                }
            }
            editLauncher.launch(intent)
        }
    }

    companion object {
        const val EXTRA_USER_NAME = "EXTRA_USER_NAME"
    }
}