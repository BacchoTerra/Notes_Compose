package com.brunobterra.notescompose.source

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brunobterra.notescompose.dao.NotesDao
import com.brunobterra.notescompose.model.Note
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Note::class],version = 1)
abstract class NotesDatabase : RoomDatabase(){

    abstract fun noteDao(): NotesDao

    companion object{

        private var INSTANCE : NotesDatabase? = null

        fun getInstance(application: Application) : NotesDatabase {


            return INSTANCE?: synchronized(this){

                val instance = Room.databaseBuilder(application,NotesDatabase::class.java,"notes_database")
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }

        }


    }

}