package com.futnote.mobile.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblNote")
data class Note(
        @field: PrimaryKey(autoGenerate = true)
        var id: Int,
        var judul: String,
        var deskripsi: String,
        var prioritas: Int
)