package com.generic.genericlab2;


import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageButton deleteButton;

    public RowViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.city_name);
        deleteButton = itemView.findViewById(R.id.delete_city);
    }

    public void bind(String cityName, View.OnClickListener deleteListener) {
        textView.setText(cityName);
        deleteButton.setOnClickListener(deleteListener);
    }
}
