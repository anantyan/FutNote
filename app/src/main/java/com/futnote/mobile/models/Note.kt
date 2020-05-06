package com.futnote.mobile.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblNote")
class Note(
        var judul: String,
        var deskripsi: String,
        var prioritas: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}