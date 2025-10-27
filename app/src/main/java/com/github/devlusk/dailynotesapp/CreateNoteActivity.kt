package com.github.devlusk.dailynotesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.devlusk.dailynotesapp.databinding.ActivityCreateNoteBinding
import com.github.devlusk.dailynotesapp.databinding.ActivityMainBinding

class CreateNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btbSave.setOnClickListener {
            val noteTitle = binding.etTitle.text
            val noteContent = binding.etContent.text


        }
    }
}