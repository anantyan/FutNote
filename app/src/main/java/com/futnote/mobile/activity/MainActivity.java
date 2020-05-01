package com.futnote.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.futnote.mobile.R;
import com.futnote.mobile.adapters.RecyclerAdapter;
import com.futnote.mobile.databinding.ActivityMainBinding;
import com.futnote.mobile.models.Note;
import com.futnote.mobile.viewmodels.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private RecyclerAdapter recyclerAdapter;
    private ActivityMainBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        getData();
    }

    private void getData() {
        // inisialisasi actionBar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("FutNote");
        }

        //inisialisasi fab
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        // inisialisasi recyclerAdapter
        recyclerAdapter = new RecyclerAdapter();

        //inisialisasi noteViewModel
        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NoteViewModel.class);
        noteViewModel.select().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                recyclerAdapter.setResult(notes);
            }
        });

        // inisialisasi recyclerView
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(recyclerAdapter);
    }
}
