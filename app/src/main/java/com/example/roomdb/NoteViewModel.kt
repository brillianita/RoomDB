package com.example.roomdb

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.database.Note
import com.example.roomdb.database.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel(), Observable{

    val notes = repository.notes
    @Bindable
    val inputTitle = MutableLiveData<String>()

    @Bindable
    val inputContent = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val deleteOrDeleteAll = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        deleteOrDeleteAll.value = "Delete All"
    }

    fun saveOrUpdateButtonText(){
        val title = inputTitle.value!!
        val content = inputContent.value!!
        insert(Note(0, title,content))
        inputTitle.value = ""
        inputContent.value = ""
    }

    fun deleteOrDeleteAllButtonText(){
        deleteAll()
    }

    private fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}