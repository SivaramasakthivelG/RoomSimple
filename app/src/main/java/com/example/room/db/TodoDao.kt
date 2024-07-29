package com.example.room.db

import android.accounts.AuthenticatorDescription
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TodoDao {

    @Query("SELECT * FROM TODO")
    fun getAllTodo(): LiveData<List<Todo>>

    @Upsert
    fun addTodo(todo: Todo)

    @Query("UPDATE Todo SET title = :title WHERE id = :id")
    fun updateTodoTitle(id: Int, title: String)

    @Query("UPDATE Todo SET description = :description WHERE id = :id")
    fun updateTodoDescription(id: Int, description: String)

    @Query("Delete FROM Todo where id=:id")
    fun deleteTodo(id: Int)
}