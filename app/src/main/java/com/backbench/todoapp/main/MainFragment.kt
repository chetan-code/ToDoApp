package com.backbench.todoapp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.backbench.todoapp.R
import com.backbench.todoapp.database.TodoDatabase
import com.backbench.todoapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentMainBinding = DataBindingUtil.inflate(inflater
                                            , R.layout.fragment_main, container, false)
        //creating view model
        val application = requireNotNull(this.activity).application
        val dataSource = TodoDatabase.getInstance(application).todoDatabaseDao
        val viewModelFactory = MainViewModelFactory(dataSource, application)
        val mainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        //set lifecycle owner
        binding.lifecycleOwner = this
        //assign value of data variables of xml
        binding.mainViewModel = mainViewModel


        val adapter = TodoAdapter(TodoListener {
            mainViewModel.updateStatus(it)
        })
        binding.todoList.adapter = adapter

        mainViewModel.todos.observe(this, Observer {
            adapter.submitList(it)
        })

        mainViewModel.navigateToAdd.observe(this , Observer {
            if(it == true){
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddFragment())
                mainViewModel.doneNavigatingToAdd()
            }
        })



        return binding.root
    }

}
