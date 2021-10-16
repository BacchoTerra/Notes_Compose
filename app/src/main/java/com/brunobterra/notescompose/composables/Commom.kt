package com.brunobterra.notescompose.composables

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.brunobterra.notescompose.ui.theme.NotesComposeTheme
import com.brunobterra.notescompose.ui.theme.hintTextColor

@Composable
fun DefaultBackground(content: @Composable () -> Unit) {

    NotesComposeTheme {

        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}


@Composable
fun MainOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    singleLine : Boolean,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions,
    keyBoardActions : KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body2,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onBackground,
            backgroundColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.onSecondary,
            unfocusedLabelColor = hintTextColor,
            focusedLabelColor = MaterialTheme.colors.onSecondary,
            focusedIndicatorColor = MaterialTheme.colors.onSecondary,
            unfocusedIndicatorColor = hintTextColor
        ),
        textStyle = MaterialTheme.typography.body2,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyBoardActions,
        isError = isError



    )

}