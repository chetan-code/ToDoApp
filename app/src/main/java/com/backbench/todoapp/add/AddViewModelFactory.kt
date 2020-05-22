package com.backbench.todoapp.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbench.todoapp.database.TodoDatabaseDao
import java.lang.IllegalArgumentException

class AddViewModelFactory (
    private val dataSource : TodoDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }
}