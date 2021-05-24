package com.example.roomdb.database

class NoteRepository(private val dao: NoteDao) {
    val notes = dao.getAllNote()

    suspend fun insert(note: Note) : Long{
        return dao.insertNote(note)
    }

    suspend fun update(note: Note) : Int{
        return dao.UpdateNote(note)
    }

    suspend fun delete(note: Note) : Int{
        return dao.deleteNote(note)
    }

    suspend fun deleteAll(): Int{
        return dao.deleteAll()
    }
}