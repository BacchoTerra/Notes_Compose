package com.brunobterra.notescompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.brunobterra.notescompose.R
import com.brunobterra.notescompose.model.Note
import com.brunobterra.notescompose.ui.theme.*
import com.brunobterra.notescompose.viewmodel.NotesViewModel
import java.util.*

@Composable
fun HomeScreen(
    notesViewModel: NotesViewModel,
    newNoteCallBack: () -> Unit) {

    DefaultBackground {

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { newNoteCallBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_note_add_24),
                        contentDescription = "search"
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    Text(
                        text = "Notes",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth(.85f),
                        textAlign = TextAlign.Start
                    )
                }

                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    items(notesViewModel.notesStateList) {

                        NoteRow(it)

                    }

                }
            }
        }

    }
}


@Composable
fun CategoryRow(
    category: String = ""
) {

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(categorySelectedBackgroundColor)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = category,
            style = MaterialTheme.typography.caption,
            color = categorySelectedForegroundColor
        )

    }
}

@Composable
fun NoteRow(note:Note) {

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = note.timestamp

    val month = calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.getDefault())
    val year = calendar.get(Calendar.YEAR)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .background(
                Brush.horizontalGradient(listOf(noteGradStartColor, noteGradEndColor)),
                MaterialTheme.shapes.medium
            )
            .border(
                1.dp,
                Brush.horizontalGradient(listOf(noteGradEndColor, noteGradStartColor)),
                MaterialTheme.shapes.medium
            )
            .padding(top = 16.dp, end = 16.dp, start = 16.dp, bottom = 8.dp)
    ) {

        Text(
            text = note.title,
            style = MaterialTheme.typography.h6,
            color = onBackground,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = note.body,
            style = MaterialTheme.typography.body2,
            color = onBackground,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 5,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            CategoryRow(note.category)

            Text(
                text = "$month. $year",
                style = MaterialTheme.typography.caption,
                color = onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                maxLines = 5,
                textAlign = TextAlign.End,
                overflow = TextOverflow.Ellipsis,
            )

        }

    }

}