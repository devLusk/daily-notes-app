package com.github.devlusk.dailynotesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.devlusk.dailynotesapp.databinding.ItemNoteBinding

class NotesAdapter(
    val notes: List<Note>,
    val onClick: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteHolder>() {

    class NoteHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NoteHolder,
        position: Int
    ) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content

        holder.binding.root.setOnClickListener {
            onClick(note)
        }
    }

    override fun getItemCount(): Int = notes.size
}
