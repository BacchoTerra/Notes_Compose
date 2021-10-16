package com.brunobterra.notescompose.repository

import com.brunobterra.notescompose.dao.NotesDao
import com.brunobterra.notescompose.model.Note

class NotesRepository (private val mDao : NotesDao) {


    val notes = mDao.selectAll()

    suspend fun insert(note:Note){
        mDao.insert(note)
    }

    suspend fun update(note:Note){
        mDao.update(note)
    }

    suspend fun delete(note:Note){
        mDao.delete(note)
    }

    suspend fun nuke(){
        mDao.nuke()
    }


}