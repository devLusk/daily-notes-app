package com.github.devlusk.dailynotesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.devlusk.dailynotesapp.databinding.ActivityCreateNoteBinding
import java.io.File

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

        val notesDir = getExternalFilesDir("notes")

        fun saveNote(title: String, content: String): Boolean {
            if (notesDir == null) {
                return false
            }

            val randomSuffix = (1000..9999).random()
            val fileName = "${title.replace(" ", "_")}_${randomSuffix}.txt".lowercase()

            val noteFile = File(notesDir, fileName)
            val noteData = "--- Title ---\n$title\n\n--- Content ---\n$content\n\n"
            noteFile.writeText(noteData)

            return true
        }

        binding.btbSave.setOnClickListener {
            val noteTitle = binding.etTitle.text.toString()
            val noteContent = binding.etContent.text.toString()

            if (noteTitle.isEmpty()) {
                Toast.makeText(this, "Please, enter a title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (noteContent.isEmpty()) {
                Toast.makeText(this, "Please, enter a content", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val saved = saveNote(noteTitle, noteContent)

            if (saved) {
                Toast.makeText(this, "Note saved successfully!", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Error saving note", Toast.LENGTH_SHORT).show()
            }
        }
    }
}