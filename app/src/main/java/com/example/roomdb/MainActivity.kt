package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb.database.Note
import com.example.roomdb.database.NoteDatabase
import com.example.roomdb.database.NoteRepository
import com.example.roomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = NoteDatabase.getInstance(application).noteDao
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)
        binding.myViewModel = noteViewModel
        binding.lifecycleOwner= this
        displayNotesList()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recyvw.layoutManager=LinearLayoutManager(this)
        displayNotesList()
    }
    private fun displayNotesList(){
        noteViewModel.notes.observe(this, Observer{
            Log.i("MYTAG", it.toString())
            binding.recyvw.adapter = NoteAdapter(it, {selectedItem:Note->listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(note: Note) {
        Toast.makeText(this, "Selected ${note.title}", Toast.LENGTH_LONG).show()
        noteViewModel.initUpdateAndDelete(note)
    }

}