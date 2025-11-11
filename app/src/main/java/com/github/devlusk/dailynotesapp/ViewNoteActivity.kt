package com.github.devlusk.dailynotesapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.devlusk.dailynotesapp.databinding.ActivityViewNoteBinding
import java.io.File

class ViewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fileName = intent.getStringExtra("note_file_name")
        val notesDir = getExternalFilesDir("notes")

        if (fileName != null && notesDir != null) {
            val file = File(notesDir, fileName)
            if (file.exists()) {
                val content = file.readText()

                val title = content.substringAfter("--- Title ---\n").substringBefore("\n\n--- Content ---")
                val noteContent = content.substringAfter("--- Content ---\n")

                binding.tvTitle.text = title
                binding.tvContent.text = noteContent
            } else {
                binding.tvTitle.text = "Note not found"
                binding.tvContent.text = ""
            }
        }
    }
}