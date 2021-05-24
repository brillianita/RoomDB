package com.example.roomdb.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note): Long

    @Update
    suspend fun UpdateNote(note: Note) : Int

    @Delete
    suspend fun deleteNote(note: Note) : Int

    @Query("DELETE FROM table_note")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM table_note")
    fun getAllNote():LiveData<List<Note>>


}