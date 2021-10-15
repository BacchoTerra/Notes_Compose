package com.brunobterra.notescompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brunobterra.notescompose.ui.theme.hintTextColor
import com.brunobterra.notescompose.ui.theme.secondaryColor


@Preview
@Composable
fun NewNoteScreen() {

    var noteTitleState by remember {
        mutableStateOf("")
    }

    var noteBodyState by remember {
        mutableStateOf("")
    }

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

            Spacer(modifier = Modifier.height(40.dp))

            MainOutlinedTextField(
                value = noteTitleState,
                hint = "Title",
                singleLine = true,
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
                value = noteBodyState,
                hint = "Body",
                singleLine = false,
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
                onClick = { /*TODO*/ }
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