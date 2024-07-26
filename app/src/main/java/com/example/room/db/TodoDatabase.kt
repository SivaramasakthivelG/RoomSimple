package com.example.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Todo::class], version = 2)
@TypeConverters(Converters::class)
abstract class TodoDatabase: RoomDatabase() {


    companion object{
        const val NAME = "Todo_DB"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Todo ADD COLUMN description TEXT NOT NULL DEFAULT ''")
            }
        }
    }

    abstract fun getTodoDao(): TodoDao





}