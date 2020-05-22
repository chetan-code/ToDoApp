package com.backbench.todoapp.main

import android.graphics.Paint
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.backbench.todoapp.R
import com.backbench.todoapp.database.Todo


//this class help us bind xml(List_item_todo) to classTodo
//we create simple extend view

@BindingAdapter("setPriority")
fun ImageView.setPriority(item: Todo?){
    item?.let {
        setImageResource(when(item.priority){
            0 -> R.drawable.ic_solid_circle_1_green_24dp
            1 -> R.drawable.ic_solid_circle_1_yellow_24dp
            2 -> R.drawable.ic_solid_circle_1_red_24dp
            else -> R.drawable.ic_solid_circle_1_black_24dp
        })
    }
}

@BindingAdapter("setmainText")
fun TextView.setMainText(item:Todo?){
    item?.let {
        text = item.mainText
    }
}

@BindingAdapter("setStatus")
fun RadioButton.setStatus(item : Todo?){
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

