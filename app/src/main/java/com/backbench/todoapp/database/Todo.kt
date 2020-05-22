package com.backbench.todoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val todoId : Long = 0L,
    @ColumnInfo(name = "main_text")
    var mainText : String = "",
    @ColumnInfo(name = "detail_text")
    var detailText : String = "",
    @ColumnInfo(name = "time_created")
    val timeCreated : Long = System.currentTimeMillis(),
    @ColumnInfo(name = "priority")
    var priority : Int = 0,
    @ColumnInfo(name = "done_status")
    var status : Boolean = false
)