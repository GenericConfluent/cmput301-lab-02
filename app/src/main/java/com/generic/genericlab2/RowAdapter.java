package com.generic.genericlab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;

public class RowAdapter extends ListAdapter<RowData, RowViewHolder> {
    // Maybe this is implemented somewhere but I couldn't find it.
    static DiffUtil.ItemCallback<RowData> DATA_DIFF = new DiffUtil.ItemCallback<RowData>() {
        @Override
        public boolean areItemsTheSame(@NonNull RowData oldItem, @NonNull RowData newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull RowData oldItem, @NonNull RowData newItem) {
            return oldItem.cityName.equals(newItem.cityName);
        }
    };

    public interface DeleteCallback {
        void delete(int uid);
    }
    final private DeleteCallback deleteCallback;

    public RowAdapter(DeleteCallback deleteCallback) {
         super(DATA_DIFF);
         this.deleteCallback = deleteCallback;
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
        RowData rowData = this.getCurrentList().get(position);
        int id = rowData.getId();
        holder.bind(rowData.cityName, (_view) -> {
            this.deleteCallback.delete(id);
        });
    }
}
