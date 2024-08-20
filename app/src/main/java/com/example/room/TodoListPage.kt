package com.example.room

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.room.db.Todo
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListPage(viewModel: TodoViewModel, navController: NavHostController) {

    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(modifier = Modifier.weight(0.7f), value = inputText, onValueChange = {
                inputText = it
            })


            Button(modifier = Modifier.padding(start = 5.dp), onClick = {
                if (inputText.trim().isNotEmpty()) {
                    viewModel.addTodo(inputText)
                    inputText = ""
                }

            }) {
                Text(text = "Add")
            }
        }

        todoList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index, item ->

                        TodoItem(item = item, navController, viewModel) {
                            viewModel.deleteTodo(item.id)
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItem(
    item: Todo,
    navController: NavHostController,
    viewModel: TodoViewModel,
    onDelete: () -> Unit
) {

    val backgroundColor = when(item.id % 3){
        0-> Color.LightGray
        1-> Color(0xCE55ECB7)
        else -> Color(0xCEB3F14C)
    }

    val context = LocalContext.current

    var showDialog by remember {
        mutableStateOf(false)
    }

    var editableText by remember {
        mutableStateOf(item.title)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(16.dp)
            .clickable {
                navController.navigate("new_page/${item.description}/${item.id}")
            },
        verticalAlignment = Alignment.CenterVertically,

        ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.Top)
        ) {
            Text(
                text = SimpleDateFormat(
                    "HH:mm:aa, dd/MM/yyyy",
                    Locale.ENGLISH
                ).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.Black,
            )
            Divider()
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = item.title,
                fontSize = 24.sp,
                color = Color(0xFF000000)
            )
        }
        IconButton(onClick = {
            editableText = item.title
            showDialog = true
        }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Delete",
                tint = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }

    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false },
            title = { Text(text = item.id.toString()) },
            text = {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    value = editableText,
                    onValueChange = {
                        editableText = it
                    })
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    item.title = editableText
                    viewModel.updateTodoTitle(item.id, title = item.title)
                }) {
                    Text(text = "Ok")
                }
            })
    }

}