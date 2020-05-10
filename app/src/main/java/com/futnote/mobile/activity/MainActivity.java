package com.futnote.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futnote.mobile.R;
import com.futnote.mobile.adapter.RecyclerAdapter;
import com.futnote.mobile.databinding.ActivityMainBinding;
import com.futnote.mobile.listener.RecyclerListener;
import com.futnote.mobile.model.Note;
import com.futnote.mobile.viewmodel.NoteViewModel;

import java.util.List;

import static com.futnote.mobile.activity.AddActivity.EXTRA_DESKRIPSI;
import static com.futnote.mobile.activity.AddActivity.EXTRA_ID;
import static com.futnote.mobile.activity.AddActivity.EXTRA_JUDUL;
import static com.futnote.mobile.activity.AddActivity.EXTRA_PRIORITAS;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private RecyclerAdapter recyclerAdapter;
    private ItemTouchHelper itemTouchHelper;
    private ActivityMainBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        funData();
    }

    private void funData() {
        // inisialisasi actionBar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("FutNote");
        }

        //inisialisasi fab
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // inisialisasi recyclerAdapter
        recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.setOnClick(new RecyclerListener() {
            @Override
            public void onClick(Note note) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra(EXTRA_ID, note.getId());
                intent.putExtra(EXTRA_JUDUL, note.getJudul());
                intent.putExtra(EXTRA_DESKRIPSI, note.getDeskripsi());
                intent.putExtra(EXTRA_PRIORITAS, note.getPrioritas());
                startActivityForResult(intent, 2);
            }
        });

        // inisialisasi swipeAdapter
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(recyclerAdapter.setOnSwipe(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note as deleted!", Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);

        //inisialisasi noteViewModel
        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NoteViewModel.class);
        noteViewModel.select().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                recyclerAdapter.setOnResult(notes);
            }
        });

        // inisialisasi recyclerView
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(recyclerAdapter);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 5 && binding.floatingActionButton.getVisibility() == View.VISIBLE) {
                    binding.floatingActionButton.hide();
                } else if (dy < 5 && binding.floatingActionButton.getVisibility() != View.VISIBLE) {
                    binding.floatingActionButton.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_theme) {
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            String judul = data.getStringExtra(EXTRA_JUDUL).trim();
            String deskripsi = data.getStringExtra(EXTRA_DESKRIPSI).trim();
            int prioritas = data.getIntExtra(EXTRA_PRIORITAS,1);

            Note note = new Note(judul, deskripsi, prioritas);
            noteViewModel.insert(note);

            Toast.makeText(this, "Note an saved!", Toast.LENGTH_SHORT).show();
        } else if(requestCode == 2 && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EXTRA_ID, -1);
            String judul = data.getStringExtra(EXTRA_JUDUL).trim();
            String deskripsi = data.getStringExtra(EXTRA_DESKRIPSI).trim();
            int prioritas = data.getIntExtra(EXTRA_PRIORITAS,1);

            if(id == -1) {
                Toast.makeText(this, "Note an saved!", Toast.LENGTH_SHORT).show();
            }

            Note note = new Note(judul, deskripsi, prioritas);
            note.setId(id);
            noteViewModel.update(note);

            Toast.makeText(this, "Note an saved!", Toast.LENGTH_SHORT).show();
        }
    }
}
