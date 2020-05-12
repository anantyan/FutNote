package com.futnote.mobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.futnote.mobile.model.Note
import com.futnote.mobile.model.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepository: NoteRepository
    private var records: LiveData<List<Note>>

    fun insert(note: Note?) {
        noteRepository.insert(note)
    }

    fun update(note: Note?) {
        noteRepository.update(note)
    }

    fun delete(note: Note?) {
        noteRepository.delete(note)
    }

    fun select(): LiveData<List<Note>> {
        return records
    }

    init {
        noteRepository = NoteRepository(application)
        records = noteRepository.select()
    }
}