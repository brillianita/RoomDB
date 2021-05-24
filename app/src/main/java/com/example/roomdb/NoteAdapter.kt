package com.example.roomdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.database.Note
import com.example.roomdb.databinding.ListItemBinding

class NoteAdapter (private val notesList: List<Note>):RecyclerView.Adapter<Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding=
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return notesList.size

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(notesList[position])

    }
}

class Holder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(note: Note){
        binding.Title.text = note.title
        binding.Content.text= note.content
    }
}