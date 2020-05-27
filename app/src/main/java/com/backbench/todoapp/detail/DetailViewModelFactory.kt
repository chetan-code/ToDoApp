package com.backbench.todoapp.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbench.todoapp.database.TodoDatabaseDao

class DetailViewModelFactory (
    private val dataSource : TodoDatabaseDao,
    private val todoKey : Long,
    private val application: Application
) : ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(dataSource,todoKey,application) as T
        }

        throw IllegalArgumentException("Unknown View Model")
    }
}