package com.example.room

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPage(itemId: Int?, itemDes: String?, viewModel: TodoViewModel) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        var changedText = remember {
            mutableStateOf(itemDes ?: "")
        }

        TextField(modifier = Modifier.fillMaxSize(), value = changedText.value, onValueChange = {
            changedText.value = it

            if (itemId != null) {
                viewModel.updateTodoDescription(itemId, it)
            }

        })


    }
}