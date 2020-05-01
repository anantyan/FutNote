package com.futnote.mobile.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.futnote.mobile.models.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM tblnote")
    void deleteAll();

    @Query("SELECT * FROM tblNote ORDER BY id DESC")
    LiveData<List<Note>> select();
}
