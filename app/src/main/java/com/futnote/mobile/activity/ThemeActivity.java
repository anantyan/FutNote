package com.futnote.mobile.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.futnote.mobile.databinding.ActivityThemeBinding;

public class ThemeActivity extends AppCompatActivity {

    private ActivityThemeBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThemeBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        funData();
    }

    private void funData() {
        // initialisasi actionBar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Theme FutNote");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // initialisasi radioButton
        binding.themeOff.setChecked(true);
        binding.themeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                binding.themeOff.setChecked(true);
            }
        });
        binding.themeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                binding.themeOn.setChecked(true);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
