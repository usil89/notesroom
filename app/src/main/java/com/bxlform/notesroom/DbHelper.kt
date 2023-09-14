package com.bxlform.notesroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bxlform.notesroom.db.daos.NoteDao
import com.bxlform.notesroom.db.entities.Note

@Database(entities = [Note::class], version = 1)
abstract class DbHelper :RoomDatabase() {

    //DAO
    abstract fun noteDao() : NoteDao

    companion object{

        const val DB_NAME = "room_database"

        private var instance : DbHelper? = null

        fun instance(context: Context) : DbHelper {

            if(instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    DbHelper::class.java,
                    DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return  instance!!
        }

    }






}