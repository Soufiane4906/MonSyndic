package com.example.monsyndic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monsyndic.R;

import java.util.List;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.ReclamationViewHolder> {

    private List<String> reclamationList;

    public ReclamationAdapter(List<String> reclamationList) {
        this.reclamationList = reclamationList;
    }

    @NonNull
    @Override
    public ReclamationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reclamation, parent, false);
        return new ReclamationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReclamationViewHolder holder, int position) {
        String reclamation = reclamationList.get(position);
        holder.reclamationTextView.setText(reclamation);
    }

    @Override
    public int getItemCount() {
        return reclamationList.size();
    }

    public static class ReclamationViewHolder extends RecyclerView.ViewHolder {

        TextView reclamationTextView;

        public ReclamationViewHolder(@NonNull View itemView) {
            super(itemView);
            reclamationTextView = itemView.findViewById(R.id.reclamation_text);
        }
    }
}
