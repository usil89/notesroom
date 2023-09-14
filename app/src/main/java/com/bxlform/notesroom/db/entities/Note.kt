package com.bxlform.notesroom.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note (


    @ColumnInfo(name = "note_title")
    var noteTitle : String,

    @ColumnInfo(name = "note_desc")
    var noteDes : String,

    @ColumnInfo(name = "note_create_date")
    var createdTime : Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    var noteId : Long? = null,

) : Serializable
