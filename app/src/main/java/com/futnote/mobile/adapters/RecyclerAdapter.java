package com.futnote.mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futnote.mobile.R;
import com.futnote.mobile.databinding.ListMainBinding;
import com.futnote.mobile.models.Note;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Note> result = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtJudul, txtDeskripsi, txtPrioritas;

        ViewHolder(ListMainBinding binding) {
            super(binding.getRoot());
            txtJudul = binding.judul;
            txtDeskripsi = binding.deskripsi;
            txtPrioritas = binding.prioritas;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListMainBinding binding = ListMainBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note resultComponent = result.get(position);
        holder.txtJudul.setText(resultComponent.getJudul());
        holder.txtDeskripsi.setText(resultComponent.getDeskripsi());
        holder.txtPrioritas.setText(String.valueOf(resultComponent.getPrioritas()));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void setResult(List<Note> result) {
        this.result = result;
        notifyDataSetChanged();
    }
}
