package com.brunobterra.notescompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    val title: String,
    val body: String,
    val category: String,
    val timestamp: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id : Int =0
){
}