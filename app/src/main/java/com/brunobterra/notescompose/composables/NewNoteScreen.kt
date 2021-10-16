package com.brunobterra.notescompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brunobterra.notescompose.model.Note
import com.brunobterra.notescompose.ui.theme.hintTextColor
import com.brunobterra.notescompose.ui.theme.secondaryColor
import com.brunobterra.notescompose.viewmodel.NotesViewModel


@Composable
fun NewNoteScreen(
    notesViewModel: NotesViewModel,
    navController: NavController
) {

    var noteTitleState by rememberSaveable() {
        mutableStateOf("")
    }

    var noteBodyState by rememberSaveable() {
        mutableStateOf("")
    }

    var noteCategoryState by rememberSaveable() {
        mutableStateOf("")
    }

    var titleErrorState by rememberSaveable {
        mutableStateOf(false)
    }

    var bodyErrorState by rememberSaveable {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()

    val focusManager = LocalFocusManager.current

    DefaultBackground {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Create",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            MainOutlinedTextField(
                value = noteTitleState,
                hint = "Title",
                singleLine = true,
                isError = titleErrorState,
                onValueChange = {
                    noteTitleState = it
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Next
                ),
                keyBoardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                })
            )

            Spacer(modifier = Modifier.height(16.dp))

            MainOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = noteCategoryState,
                hint = "Category (Optional)",
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Next
                ),
                keyBoardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                onValueChange = {
                    noteCategoryState = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            MainOutlinedTextField(
                value = noteBodyState,
                hint = "Body",
                singleLine = false,
                isError = bodyErrorState,
                onValueChange = { noteBodyState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None

                ),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary
                ),
                onClick = {

                    val note = Note(noteTitleState, noteBodyState, noteCategoryState)

                    if (notesViewModel.checkIfNoteCanBeSaved(note)) {
                        notesViewModel.insert(note)
                        navController.navigateUp()
                    } else {
                        titleErrorState = noteTitleState.isEmpty()
                        bodyErrorState = noteBodyState.isEmpty()
                    }

                }
            ) {
                Text(
                    text = "Salvar",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

    }

}