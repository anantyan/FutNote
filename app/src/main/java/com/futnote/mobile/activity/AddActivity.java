package com.futnote.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.futnote.mobile.R;
import com.futnote.mobile.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {

    private ActivityAddBinding binding;
    private View view;
    private Intent intent;
    public static final String EXTRA_ID = "ADD_ID"; // khusus jika edit data
    public static final String EXTRA_JUDUL = "ADD_JUDUL";
    public static final String EXTRA_DESKRIPSI = "ADD_DESKRIPSI";
    public static final String EXTRA_PRIORITAS = "ADD_PRIORITAS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        funData();
    }

    private void funData() {
        // set intent
        intent = getIntent();

        // initialisasi actionBar
        if(getSupportActionBar() != null) {
            if(intent.hasExtra(EXTRA_ID)) {
                getSupportActionBar().setTitle("Edit FutNote"); // khusus jika edit data
            } else {
                getSupportActionBar().setTitle("Add FutNote");
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // set default value
        binding.prioritas.setMinValue(1);
        binding.prioritas.setMaxValue(10);
        binding.judul.setText(intent.getStringExtra(EXTRA_JUDUL));
        binding.deskripsi.setText(intent.getStringExtra(EXTRA_DESKRIPSI));
        binding.prioritas.setValue(intent.getIntExtra(EXTRA_PRIORITAS, 1));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_save) {
            // inisialisasi
            int dataId = getIntent().getIntExtra(EXTRA_ID, -1); // khusus jika edit data
            String judul = binding.judul.getText().toString().trim();
            String deskripsi = binding.deskripsi.getText().toString().trim();
            int prioritas = binding.prioritas.getValue();

            // set required field
            if(judul.isEmpty() || deskripsi.isEmpty()) {
                Toast.makeText(this, "Harud mengisi judul dan deskripsi!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_JUDUL, judul);
                intent.putExtra(EXTRA_DESKRIPSI, deskripsi);
                intent.putExtra(EXTRA_PRIORITAS, prioritas);

                if(dataId != -1) {
                    intent.putExtra(EXTRA_ID, dataId);
                }

                setResult(RESULT_OK, intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
