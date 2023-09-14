package com.bxlform.notesroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bxlform.notesroom.adapter.NoteAdapter
import com.bxlform.notesroom.databinding.ActivityMainBinding
import com.bxlform.notesroom.db.entities.Note

class MainActivity : AppCompatActivity(), NoteAdapter.NoteItemListener {

    private val viewModel : MainViewModel by viewModels { MainViewModelFactory(this) }

    private lateinit var binding : ActivityMainBinding

    private lateinit var adapter: NoteAdapter

       private val startForResult = registerForActivityResult(
           ActivityResultContracts.StartActivityForResult()
       ){
           if (it.resultCode == RESULT_OK) {
               val note = it.data?.getSerializableExtra(UpdateActivity.NOTE_TAG) as Note
               viewModel.updateNote(note)
           }
       }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnAddMainActivity.setOnClickListener{
            val newNote = Note(
                binding.etTitleMainActivity.text.toString(),
                binding.etDescriptionMainActivity.text.toString(),
            )
            viewModel.addNotes(newNote)

        }



        viewModel.notes.observe(this) {
            adapter.updateNotes(it)
        }

        binding.rvNotesMainActivity.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(this)
        binding.rvNotesMainActivity.adapter = adapter



        //setListener()
        //observeVm()
        //setRv()



        setContentView(binding.root)
    }

    override fun onDeleteNote(id : Long) {
        viewModel.removeNote(id)
        Toast.makeText(this, "Delete ! $id", Toast.LENGTH_LONG).show()
    }

    override fun onUpdateNote(note : Note) {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("note", note)
        startForResult.launch(intent)
    }

    /*private fun setRv() {


    }*/

    /*private fun observeVm() {
        viewModel.notes.observe(this, Observer {
            adapter.updateNotes(it.toMutableList())

        })
    }*/

    /*private fun setListener() {

    }*/
}