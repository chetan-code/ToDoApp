package com.backbench.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDatabaseDao{

    @Insert
    fun insert(todo : Todo)

    @Update
    fun update(todo : Todo)

    @Query("SELECT * FROM todo_table ORDER BY todoId DESC")
    fun getAllTodos() : LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE todoId = :key")
    fun getTodo(key : Long) : Todo?

    @Query("DELETE FROM todo_table WHERE todoId = :key")
    fun deleteTodoByKey(key :Long)

}