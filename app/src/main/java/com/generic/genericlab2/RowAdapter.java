package com.generic.genericlab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;

public class RowAdapter extends ListAdapter<String, RowViewHolder> {
    ArrayList<String> cities;

    // Maybe this is implemented somewhere but I couldn't find it.
    static DiffUtil.ItemCallback<String> STRING_DIFF = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            // Probably a better way to do this but I don't know if objects store
            // ids.
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    };

    public RowAdapter(ArrayList<String> cities) {
         super(STRING_DIFF);
         this.cities = cities;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // QUESTION: What does the attachToRoot parameter do exactly?
        // NOTE: Better to use view here because we don't actually rely on any properties of the
        // LinearLayout. Makes changing more convenient later.
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content, parent, false);
        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        holder.bind(cities.get(position), (view) -> {
            cities.remove(position);
            this.notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
