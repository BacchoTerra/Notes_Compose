package com.brunobterra.notescompose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.brunobterra.notescompose.model.Note
import com.brunobterra.notescompose.repository.NotesRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class NotesViewModel(private val repo: NotesRepository) : ViewModel() {

    val notesList = repo.notes
    val notesStateList = mutableStateListOf<Note>()


    fun insert(note: Note) = viewModelScope.launch {
        repo.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        repo.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        repo.delete(note)
    }

    fun nuke() = viewModelScope.launch {
        repo.nuke()
    }

    fun updateNotesListState(list: List<Note>) {
        notesStateList.clear()
        notesStateList.addAll(list)
    }

    fun checkIfNoteCanBeSaved(note : Note) : Boolean {
        return note.title.isNotEmpty() && note.body.isNotEmpty()
    }
}

class NotesViewModelFactory(private val repo: NotesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(repo) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}