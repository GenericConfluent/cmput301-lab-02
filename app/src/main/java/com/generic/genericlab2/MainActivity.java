package com.generic.genericlab2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {
    // This is better for long lists. I'm not sure if it introduces significant
    // overhead for small lists compared to ListView.
    RecyclerView cityList;
    EditText cityNameField;
    ImageButton submitButton;

    RowAdapter listAdapter;
    ArrayList<RowData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // List add callbacks
        cityNameField = findViewById(R.id.city_input);
        submitButton = findViewById(R.id.submit_button);


        submitButton.setOnClickListener((_view) -> {
            var text = cityNameField.getText().toString();
            if (text.isBlank()) return;
            this.addCity(text);
            cityNameField.setText("");
        });

        // Setup list
        cityList = findViewById(R.id.city_list);

        dataList = Stream.of("Edmonton", "Vancouver", "Calgary", "Toronto", "Montreal", "Quebec")
                .map(RowData::new)
                .collect(Collectors.toCollection(ArrayList::new));

        listAdapter = new RowAdapter(this::deleteCity);
        cityList.setAdapter(listAdapter);
        cityList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        listAdapter.submitList(dataList);
    }

    private void deleteCity(int id) {
        for (int i = 0; i < dataList.size(); ++i) {
            if (dataList.get(i).getId() == id) {
                dataList.remove(i);
                // WARN: DO NOT REPLACE WITH submitList. It ignores the call when resubmitting the same
                // list instance. This could optionally be replaced with notifyDatasetChanged but
                // the existence of specialized methods probably means we can do some more performant
                // stuff by informing the listAdapter of what exactly happened to the list.
                listAdapter.notifyItemRemoved(i);
                return;
            }
        }
    }

    private void addCity(String name) {
        dataList.add(0, new RowData(name));
        listAdapter.notifyItemInserted(0);
    }
}