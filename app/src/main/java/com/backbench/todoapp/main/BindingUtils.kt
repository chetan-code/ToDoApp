package com.backbench.todoapp.main

import android.annotation.SuppressLint
import android.graphics.Paint
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.backbench.todoapp.R
import com.backbench.todoapp.database.Todo
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat


//this class help us bind xml(List_item_todo) to classTodo
//we create simple extend view

@SuppressLint("SimpleDateFormat")
@BindingAdapter("setFormattedTime")
fun TextView.convertLongToDateString(systemTime: Long?){
    systemTime?.let {
        text = SimpleDateFormat("hh:mm a \ndd/mm/yyyy '('EEEE')'")
                      .format(systemTime).toString()
    }
}


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

@BindingAdapter("setChipColor")
fun Chip.setChipColor(item: Todo?){
    item?.let{
        setChipBackgroundColorResource( when(item.priority){
            0 -> R.color.green
            1 -> R.color.yellow
            else -> R.color.red
        })
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

