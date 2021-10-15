package com.brunobterra.notescompose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {


    val selectedChipState = mutableStateOf(0)
    val categoriesStateList = mutableStateListOf<String>("Android", "Android", "Android", "Android", "Android", "Android", "Android")

    val notesStateList = mutableStateListOf<Int>(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)

}