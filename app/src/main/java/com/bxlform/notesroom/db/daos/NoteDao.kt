package com.bxlform.notesroom.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bxlform.notesroom.db.entities.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY noteId DESC" )
    suspend  fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes_table WHERE noteId LIKE :id")
    suspend fun getNote(id : Long) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note) : Long

    @Update
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM notes_table WHERE noteId = :id")
    suspend fun deleteNote(id : Long)





}