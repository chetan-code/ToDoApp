package com.backbench.todoapp.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbench.todoapp.database.TodoDatabaseDao
import java.lang.IllegalArgumentException

class MainViewModelFactory (
    private val dataSource : TodoDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknowm ViewModel class")
    }
}