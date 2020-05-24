package com.backbench.todoapp.main

import android.graphics.Paint
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.backbench.todoapp.R
import com.backbench.todoapp.database.Todo
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.chip.Chip


//this class help us bind xml(List_item_todo) to classTodo
//we create simple extend view

@BindingAdapter("setPriority")
fun Chip.setPriority(item: Todo?){
    item?.let {
        text = when(item.priority){
            0 -> "CASUAL"
            1 -> "IMPORTANT"
            else -> "URGENT"
        }
    }
}

@BindingAdapter("setmainText")
fun TextView.setMainText(item:Todo?){
    item?.let {
        text = item.mainText
    }
}

@BindingAdapter("setStatus")
fun MaterialCheckBox.setStatus(item : Todo?){
    item?.let{
        isChecked = item.status
    }
}

@BindingAdapter("setTextStyle")
fun TextView.setTextStyle(item:Todo?){
    item?.let {
        paintFlags = when(item.status){
            true -> Paint.STRIKE_THRU_TEXT_FLAG
            false -> paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}

