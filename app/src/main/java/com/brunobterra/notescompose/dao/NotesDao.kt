package com.brunobterra.notescompose.dao

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.room.*
import com.brunobterra.notescompose.model.Note

@Dao
interface NotesDao {

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun nuke()

    @Query("SELECT * FROM note_table")
    fun selectAll() : LiveData<List<Note>>
}