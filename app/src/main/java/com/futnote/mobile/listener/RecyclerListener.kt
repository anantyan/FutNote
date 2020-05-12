package com.futnote.mobile.listener

import com.futnote.mobile.model.Note

interface RecyclerListener {
    fun onClick(note: Note?)
}