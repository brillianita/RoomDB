package com.example.roomdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_note")

data class Note (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id : Int,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "content")
    var content : String
)