package com.example.roomdb.database

class NoteRepository(private val dao: NoteDao) {
    val notes = dao.getAllNotes()

    suspend fun insert(note: Note) {
        dao.insertNote(note)
    }

    suspend fun update(note: Note) {
        dao.UpdateNote(note)
    }

    suspend fun delete(note: Note) {
        dao.deleteNote(note)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}