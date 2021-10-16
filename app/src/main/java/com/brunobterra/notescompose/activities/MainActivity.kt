package com.brunobterra.notescompose.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brunobterra.notescompose.application.NotesApplication
import com.brunobterra.notescompose.composables.HomeScreen
import com.brunobterra.notescompose.composables.NewNoteScreen
import com.brunobterra.notescompose.viewmodel.NotesViewModel
import com.brunobterra.notescompose.viewmodel.NotesViewModelFactory

class MainActivity : ComponentActivity() {

    private val notesViewModel by viewModels<NotesViewModel> {
        NotesViewModelFactory((application as NotesApplication).mRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notesViewModel.notesList.observe(this){
            notesViewModel.updateNotesListState(it)
        }

        notesViewModel.nuke()

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home") {


                composable("home") {
                    HomeScreen(notesViewModel) {
                        navController.navigate("new")
                    }
                }

                composable("new") {
                    NewNoteScreen(notesViewModel,navController)
                }

            }
        }
    }
}

