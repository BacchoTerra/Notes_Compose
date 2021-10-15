package com.brunobterra.notescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brunobterra.notescompose.composables.HomeScreen
import com.brunobterra.notescompose.composables.NewNoteScreen
import com.brunobterra.notescompose.ui.theme.NotesComposeTheme
import com.brunobterra.notescompose.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home"){


                composable("home"){
                    HomeScreen(homeViewModel) {
                        navController.navigate("new")
                    }
                }

                composable("new"){
                    NewNoteScreen()
                }

            }

        }
    }
}

