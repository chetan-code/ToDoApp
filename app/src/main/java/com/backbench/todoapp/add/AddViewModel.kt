package com.backbench.todoapp.add

import android.app.Application
import android.os.Debug
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.backbench.todoapp.database.Todo
import com.backbench.todoapp.database.TodoDatabase
import com.backbench.todoapp.database.TodoDatabaseDao
import kotlinx.coroutines.*

class AddViewModel(
    val database: TodoDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    var mainEditText = MutableLiveData<String>()
    var detailEditText = MutableLiveData<String>()
    private val _priority = MutableLiveData<Int>()
    val priority: LiveData<Int>
        get() = _priority


    private val _showSnackbar = MutableLiveData<Boolean>()
    val showSnackbar: LiveData<Boolean>
        get() = _showSnackbar
    fun doneShowingSnackbar(){
        _showSnackbar.value = false
    }


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val todos = database.getAllTodos()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    fun doneNavigatingToMain() {
        _navigateToMain.value = false
    }

    init {
        _navigateToMain.value = false
        _priority.value = 0
    }

    fun setPriority(value: Int) {
        _priority.value = value
    }

    fun addTaskToTodo() {

        uiScope.launch {
            val todo = Todo()
            if(mainEditText.value == null||mainEditText.value.equals("")){
                _showSnackbar.value = true
                return@launch
            }
            todo.mainText = mainEditText.value ?: return@launch
            todo.detailText = detailEditText.value ?: ""
            todo.priority = priority.value ?: 0
            insert(todo)
            _navigateToMain.value = true
        }
    }

    private suspend fun insert(todo: Todo) {
        withContext(Dispatchers.IO) {
            database.insert(todo)
            Log.i("AddViewModel","Added data to todo")
        }
    }


}