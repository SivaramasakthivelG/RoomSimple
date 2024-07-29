package com.example.room

import android.accounts.AuthenticatorDescription
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.db.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel: ViewModel() {

    val todoDao = MainApplication.todoDatabase.getTodoDao()

    val todoList: LiveData<List<Todo>> = todoDao.getAllTodo()

    fun addTodo(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(Todo(title = title, createdAt = Date.from(Instant.now()), description = ""))
        }
    }

    fun updateTodoTitle(id: Int, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.updateTodoTitle(id, title)
        }
    }
    fun updateTodoDescription(id: Int, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.updateTodoDescription(id, description)
        }
    }


    fun deleteTodo(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }

}