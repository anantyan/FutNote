package com.futnote.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.futnote.mobile.R;
import com.futnote.mobile.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {

    private ActivityAddBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        getData();
    }

    private void getData() {
        //initialisasi actionBar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add FutNote");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.prioritas.setMinValue(1);
        binding.prioritas.setMaxValue(10);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
