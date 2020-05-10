package com.futnote.mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futnote.mobile.databinding.ListMainBinding;
import com.futnote.mobile.listener.RecyclerListener;
import com.futnote.mobile.model.Note;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static List<Note> result = new ArrayList<>();
    private RecyclerListener listener;

    public void setOnResult(List<Note> result) {
        this.result = result;
        notifyDataSetChanged();
    }

    public void setOnClick(RecyclerListener listener) {
        this.listener = listener;
    }

    public Note setOnSwipe(int position) {
        return result.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtJudul, txtDeskripsi, txtPrioritas;

        ViewHolder(ListMainBinding binding, final RecyclerListener listener) {
            super(binding.getRoot());
            txtJudul = binding.judul;
            txtDeskripsi = binding.deskripsi;
            txtPrioritas = binding.prioritas;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onClick(result.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListMainBinding binding = ListMainBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, listener);
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
}
