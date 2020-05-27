package com.backbench.todoapp.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.backbench.todoapp.database.Todo
import com.backbench.todoapp.database.TodoDatabaseDao
import kotlinx.coroutines.*

class DetailViewModel(
    val database : TodoDatabaseDao,
    val todoKey :Long,
    application : Application
) : AndroidViewModel(application){

    var currentTodo = MutableLiveData<Todo?>()

    //for coroutine
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        initializeTodo()
    }

    private fun initializeTodo(){
        uiScope.launch {
            currentTodo.value = getTodoFromDatabase()
        }
    }

    private suspend fun getTodoFromDatabase() : Todo?{

        return withContext(Dispatchers.IO){
            var todo = database.getTodo(todoKey)
            todo
        }
    }


}