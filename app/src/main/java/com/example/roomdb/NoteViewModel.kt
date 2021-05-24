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
    private var isUpdateOrDelete = false
    private lateinit var noteToUpdateOrDelete : Note
    @Bindable
    val inputTitle = MutableLiveData<String>()

    @Bindable
    val inputContent = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val deleteOrDeleteAllButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        deleteOrDeleteAllButtonText.value = "Delete All"
    }

    fun saveOrUpdateButtonText(){
        if(isUpdateOrDelete){
            noteToUpdateOrDelete.title = inputTitle.value!!
            noteToUpdateOrDelete.content = inputContent.value!!
            update(noteToUpdateOrDelete)
        }else{
            val title = inputTitle.value!!
            val content = inputContent.value!!
            insert(Note(0, title,content))
            inputTitle.value = ""
            inputContent.value = ""
        }

    }

    fun deleteOrDeleteAllButtonText(){
        if(isUpdateOrDelete){
            delete(noteToUpdateOrDelete)
        } else{
            deleteAll()

        }
    }

    private fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
        inputTitle.value=null
        inputContent.value=null
        isUpdateOrDelete=false
        noteToUpdateOrDelete=note
        saveOrUpdateButtonText.value = "Save"
        deleteOrDeleteAllButtonText.value = "Delete All"
    }

    fun delete(note: Note) = viewModelScope.launch {
        inputTitle.value=null
        inputContent.value=null
        isUpdateOrDelete=false
        noteToUpdateOrDelete=note
        saveOrUpdateButtonText.value = "Save"
        deleteOrDeleteAllButtonText.value = "Delete All"
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUpdateAndDelete(note: Note){
        inputTitle.value=note.title
        inputContent.value=note.content
        isUpdateOrDelete=true
        noteToUpdateOrDelete=note
        saveOrUpdateButtonText.value = "Update"
        deleteOrDeleteAllButtonText.value = "Delete"
    }
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}