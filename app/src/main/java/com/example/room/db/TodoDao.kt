package com.example.room.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TodoDao {

    @Query("SELECT * FROM TODO")
    fun getAllTodo(): LiveData<List<Todo>>

    @Upsert
    fun addTodo(todo: Todo)

    @Query("Delete FROM Todo where id=:id")
    fun deleteTodo(id: Int)
}