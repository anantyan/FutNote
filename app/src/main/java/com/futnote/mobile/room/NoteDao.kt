package com.futnote.mobile.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.futnote.mobile.model.Note

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note?)

    @Update
    fun update(note: Note?)

    @Delete
    fun delete(note: Note?)

    @Query("SELECT * FROM tblNote ORDER BY id DESC")
    fun select(): LiveData<List<Note?>?>?
}