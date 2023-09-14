package com.bxlform.notesroom

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bxlform.notesroom.db.daos.NoteDao
import com.bxlform.notesroom.db.entities.Note
import kotlinx.coroutines.launch

class MainViewModel(private val dao : NoteDao) : ViewModel() {

    private val _notes : MutableLiveData<List<Note>> = MutableLiveData(mutableListOf())
    val notes : LiveData<List<Note>>
        get() = _notes

    init {
        viewModelScope.launch {
            val notes = dao.getAllNotes()
            _notes.value = notes
        }
    }

    fun addNotes(note : Note) {
        viewModelScope.launch {
            val id = dao.insertNote(note)
            note.noteId = id

            val newValue = _notes.value!!.toMutableList()
            newValue.add(note)
            _notes.value = newValue
        }
    }

    fun removeNote(id : Long) {
        viewModelScope.launch {
            dao.deleteNote(id)

            val newValue = _notes.value!!.filter { n -> n.noteId != id }
            _notes.value = newValue
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch{
            dao.updateNote(note)

            var newValue = _notes.value!!
            newValue = newValue.map {
                if (it.noteId == note.noteId) {
                    return@map note
                }
                return@map it
            }
            _notes.value = newValue
        }

    }


}

class MainViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(DbHelper.instance(context).noteDao()) as T
    }
}