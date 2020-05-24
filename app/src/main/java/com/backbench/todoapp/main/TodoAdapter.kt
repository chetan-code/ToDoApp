package com.backbench.todoapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbench.todoapp.database.Todo
import com.backbench.todoapp.databinding.FragmentAddBinding
import com.backbench.todoapp.databinding.ListItemTodoBinding
import com.backbench.todoapp.generated.callback.OnClickListener

class TodoAdapter(val clickListener: TodoListener, val deleteListener: DeleteListener) : ListAdapter<Todo,TodoAdapter.ViewHolder>(TodoDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = getItem(position)
        holder.bind(todoItem, clickListener, deleteListener)
    }

    //ViewHolder
    class ViewHolder private constructor(val binding: ListItemTodoBinding) : RecyclerView.ViewHolder(binding.root){

        //onBindViewHolder logic inside viewholder
        fun bind(item : Todo, clickListener: TodoListener, deleteListener: DeleteListener){
            binding.todo = item
            binding.clickListener = clickListener
            binding.deleteClickListener = deleteListener
            binding.executePendingBindings()
        }

        companion object{
            //onCreateViewHolder logic in ViewHolder
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater  = LayoutInflater.from(parent.context)
                val binding = ListItemTodoBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }

        }
    }

}
//clicklistener
class TodoListener(val clickListener: (sleepId : Long) -> Unit){
    fun onClick(todo : Todo) = clickListener(todo.todoId)
}

class DeleteListener(val clickListener: (sleepId : Long) -> Unit){
    fun onClick(todo : Todo) = clickListener(todo.todoId)
}


//diff utils -> help use compare list items using mayers algo and animate views
class TodoDiffCallback : DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.todoId == newItem.todoId
    }
    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}

