package com.brunobterra.notescompose.composables

import android.widget.Toast
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brunobterra.notescompose.R
import com.brunobterra.notescompose.ui.theme.*
import com.brunobterra.notescompose.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    newNoteCallBack: () -> Unit) {

    DefaultBackground {

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { newNoteCallBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
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

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                    )

                }

                LazyRow(modifier = Modifier.fillMaxWidth()) {

                    itemsIndexed(homeViewModel.categoriesStateList) { i: Int, s: String ->

                        CategoryRow(
                            selectedChipIndex = homeViewModel.selectedChipState.value,
                            currentChipIndex = i
                        ) {
                            homeViewModel.selectedChipState.value = i
                        }

                    }

                }

                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    items(homeViewModel.notesStateList) {

                        NoteRow()

                    }

                }
            }
        }

    }
}


@Composable
fun CategoryRow(
    category: String = "Composable",
    selectedChipIndex: Int,
    currentChipIndex: Int,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (currentChipIndex == selectedChipIndex)
                    categorySelectedBackgroundColor
                else
                    categoryNotSelectedBackgroundColor,
            )
            .clickable {
                onClick()
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = category,
            style = MaterialTheme.typography.caption,
            color = if (currentChipIndex == selectedChipIndex)
                categorySelectedForegroundColor
            else
                categoryNotSelectedForegroundColor
        )

    }
}

@Composable
fun NoteRow() {

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
            text = "Title",
            style = MaterialTheme.typography.h6,
            color = onBackground,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "This will be the very large message but it will have a maximum number of lines, and it will be elipsized at the end, i think 5 lines is enough for a good visualization. After this, the user will have to click to open the note and see the entire thing.",
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

            CategoryRow(selectedChipIndex = -1, currentChipIndex = -2) {}

            Text(
                text = "Oct. 2021",
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