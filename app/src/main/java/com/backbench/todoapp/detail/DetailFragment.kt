package com.backbench.todoapp.detail

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.backbench.todoapp.R
import com.backbench.todoapp.database.TodoDatabase
import com.backbench.todoapp.databinding.FragmentDetailBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)


        val application = requireNotNull(this.activity).application
        val dataSource = TodoDatabase.getInstance(application).todoDatabaseDao
        val todoKey = DetailFragmentArgs.fromBundle(arguments!!).todoKey
        val viewModelFactory = DetailViewModelFactory(dataSource,todoKey,application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)

        binding.lifecycleOwner = this
        binding.detailViewModel = viewModel

        return binding.root
    }

}
