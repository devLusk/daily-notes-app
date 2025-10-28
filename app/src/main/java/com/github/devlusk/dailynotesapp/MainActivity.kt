package com.github.devlusk.dailynotesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.devlusk.dailynotesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val createNoteLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val notesQuantity = data?.getIntExtra("notes_quantity", 0)

                binding.tvCount.text = "You have $notesQuantity notes"
            }
        }

        binding.btnAddNote.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            createNoteLauncher.launch(intent)
        }

        binding.btnAllNote.setOnClickListener {
            val intent = Intent(this, NotesListActivity::class.java)
            startActivity(intent)
        }
    }
}