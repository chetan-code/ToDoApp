package com.backbench.todoapp.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.backbench.todoapp.database.Todo
import com.backbench.todoapp.database.TodoDatabaseDao
import kotlinx.coroutines.*


class MainViewModel(
    val database: TodoDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val todos : LiveData<List<Todo>> = database.getAllTodos()

    private val _navigateToAdd = MutableLiveData<Boolean>()
    val navigateToAdd: LiveData<Boolean>
        get() = _navigateToAdd

    fun NavigateToAdd() {
        _navigateToAdd.value = true
    }

    fun doneNavigatingToAdd() {
        _navigateToAdd.value = false
    }


    init {
        _navigateToAdd.value = false
    }


    fun updateStatus(todoId : Long){
        uiScope.launch {
            update(todoId)
        }
    }

    private suspend fun update(todoId : Long){
        withContext(Dispatchers.IO){
            val todo: Todo = database.getTodo(todoId) ?: return@withContext
            todo.status = !todo.status
            database.update(todo)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}