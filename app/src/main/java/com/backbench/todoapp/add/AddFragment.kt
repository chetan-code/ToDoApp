package com.backbench.todoapp.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.backbench.todoapp.R
import com.backbench.todoapp.database.TodoDatabase
import com.backbench.todoapp.databinding.FragmentAddBinding
import com.backbench.todoapp.hideKeyboard
import com.google.android.material.snackbar.Snackbar


class AddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding : FragmentAddBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = TodoDatabase.getInstance(application).todoDatabaseDao
        val viewModelFactory = AddViewModelFactory(dataSource,application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(AddViewModel::class.java)

        binding.lifecycleOwner = this

        binding.addViewModel = viewModel

        viewModel.priority.observe(this, Observer {
            setButtonColor(it, binding)
        })

        viewModel.navigateToMain.observe(this, Observer {
            if(it){
                hideKeyboard()
                this.findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
            }
        })

        viewModel.showSnackbar.observe(this , Observer {
            if(it){
                hideKeyboard()
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.null_title_error),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })

        return binding.root
    }

    private fun setButtonColor(value : Int, binding: FragmentAddBinding){

        when(value){
            0 -> {
                with(binding) {
                    lowButton.setBackgroundColor(resources.getColor(R.color.green))
                    mediumButton.setBackgroundColor(resources.getColor(R.color.design_default_color_background))
                    highButton.setBackgroundColor(resources.getColor(R.color.design_default_color_background))
                }
            }
            1 -> {
                with(binding) {
                    lowButton.setBackgroundColor(resources.getColor(R.color.design_default_color_background))
                    mediumButton.setBackgroundColor(resources.getColor(R.color.yellow))
                    highButton.setBackgroundColor(resources.getColor(R.color.design_default_color_background))
                }
            }
            2 -> {
                with(binding) {
                    lowButton.setBackgroundColor(resources.getColor(R.color.design_default_color_background))
                    mediumButton.setBackgroundColor(resources.getColor(R.color.design_default_color_background))
                    highButton.setBackgroundColor(resources.getColor(R.color.red))
                }
            }
        }
    }


}
