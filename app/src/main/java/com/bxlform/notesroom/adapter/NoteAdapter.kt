package com.bxlform.notesroom.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bxlform.notesroom.R
import com.bxlform.notesroom.db.entities.Note
import java.util.Date

class NoteAdapter(private val noteItemListener: NoteItemListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>( ){

    interface NoteItemListener {
        fun onDeleteNote(id : Long)
        fun onUpdateNote(note : Note)
    }

    private var notes : List<Note> = listOf()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

       val tvTitle : TextView = view.findViewById(R.id.tv_title_item_view)
        val tvDescription :TextView = view.findViewById(R.id.tv_Description_item_view)
        val tvCreatedTime : TextView = view.findViewById(R.id.tv_timeCreated_item_view)

        val btnUpdate : ImageButton = view.findViewById(R.id.btn_update_item_view)
        val btnDelete : ImageButton = view.findViewById(R.id.btn_delete_item_view)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater .from(parent.context)
                                    .inflate(R.layout.item_view, parent, false)

        return  ViewHolder((view))

    }


    override fun getItemCount(): Int {
        return  notes.size
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val note  = notes[position]
        holder.tvTitle.text = note.noteTitle
        holder.tvDescription.text = note.noteDes
        holder.tvCreatedTime.text = Date(note.createdTime).toString()

        holder.btnDelete.setOnClickListener {
            noteItemListener.onDeleteNote(note.noteId!!)
        }

        holder.btnUpdate.setOnClickListener {
            noteItemListener.onUpdateNote(note)
        }

    }

    fun updateNotes(notes : List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}