package com.example.room

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.room.db.TodoDatabase
import com.example.room.db.TodoDatabase.Companion.MIGRATION_1_2

class MainApplication : Application() {

    companion object {
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME)
            .addMigrations(MIGRATION_1_2)
            .build()

    }

}