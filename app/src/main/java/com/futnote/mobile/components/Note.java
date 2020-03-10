package com.futnote.mobile.components;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblNote")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String judul;
    private String deskripsi;
    private int prioritas;

    public Note(int id, String judul, String deskripsi, int prioritas) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.prioritas = prioritas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(int prioritas) {
        this.prioritas = prioritas;
    }
}
