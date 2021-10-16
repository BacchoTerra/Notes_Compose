package com.brunobterra.notescompose.application

import android.app.Application
import com.brunobterra.notescompose.repository.NotesRepository
import com.brunobterra.notescompose.source.NotesDatabase

class NotesApplication : Application(){

    private lateinit var mDatabase : NotesDatabase

    lateinit var mRepository :NotesRepository

    override fun onCreate() {
        super.onCreate()

        mDatabase = NotesDatabase.getInstance(this)
        mRepository = NotesRepository(mDatabase.noteDao())
    }

}