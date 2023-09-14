package com.bxlform.notesroom

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.bxlform.notesroom.databinding.ActivityUpdateBinding
import com.bxlform.notesroom.db.entities.Note

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    /*private val noteDb : DbHelper by lazy {
        Room.databaseBuilder(this,DbHelper::class.java,DbHelper.DB_NAME  )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private lateinit var note : Note
    private var noteId= 0
    private var defaultTitle = ""
    private var defaultDesc = ""*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        binding = ActivityUpdateBinding.inflate(layoutInflater)

        val note = intent.getSerializableExtra(NOTE_TAG) as Note
        val position = intent.getIntExtra(POSITION_TAG, -1)

        binding.etDescriptionUpdateActivity.setText(note.noteDes)
        binding.etTitleUpdateView.setText(note.noteTitle)

        binding.btnUpdateUpdateActivity.setOnClickListener {
            note.noteTitle = binding.etTitleUpdateView.text.toString()
            note.noteDes = binding.etDescriptionUpdateActivity.text.toString()
            val intent = Intent()
            intent.putExtra(NOTE_TAG, note)
            setResult(RESULT_OK, intent)
            finish()
        }

        setContentView(binding.root)

       /* intent.extras?.let {
            noteId = it.getInt("bundle_note_id")
        }*/

        setRv()


    }

    private fun setRv() {

    }

    companion object {
        val NOTE_TAG = "note"

        val POSITION_TAG = "position"

        val NOTE_TITLE: String?
            get() {
                TODO()
            }

        val NOTE_DESCRIPTION: String?
            get() {
                TODO()
            }
    }
}