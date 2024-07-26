package com.example.room

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.room.db.TodoDao
import com.example.room.db.TodoDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPage(itemDes: String?) {
    val db = MainApplication.todoDatabase.getTodoDao()


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var changedText = remember {
            mutableStateOf(itemDes?: "")
        }

        TextField(value = changedText.value, onValueChange = {
            changedText.value = it
        })


    }
}