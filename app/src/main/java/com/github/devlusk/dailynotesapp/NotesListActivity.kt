package com.github.devlusk.dailynotesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.devlusk.dailynotesapp.databinding.ActivityNotesListBinding

class NotesListActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNotesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvNotes.layoutManager = LinearLayoutManager(this)

        val notesDir = getExternalFilesDir("notes")
        val notes = mutableListOf<Note>()

        notesDir?.listFiles()?.forEach { file ->
            if (file.extension == "txt") {
                val content = file.readText()

                val title = Regex(
                    "--- Title ---\\n(.*?)\\n\\n"
                ).find(content)?.groupValues?.get(1) ?: "No Title"

                val noteContent = Regex(
                    "--- Content ---\\n(.*?)\\n\\n", RegexOption.DOT_MATCHES_ALL
                ).find(content)?.groupValues?.get(1) ?: "No Content"
                
                notes.add(Note(title, noteContent, file.absolutePath))
            }
        }

        val adapter = NotesAdapter(notes)
        binding.rvNotes.adapter = adapter

        binding.btnAddNote.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}