package com.generic.genericlab2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // This is better for long lists. I'm not sure if it introduces significant
    // overhead for small lists compared to ListView.
    RecyclerView cityList;
    RowAdapter listAdapter;
    ArrayList<String> dataList;
    int nextIdentifier = 0;

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

        // Setup list
        cityList = findViewById(R.id.city_list);
        dataList = new ArrayList<String>(Arrays.asList("Edmonton", "Vancouver", "Calgary", "Toronto", "Montreal", "Quebec"));

        listAdapter = new RowAdapter(dataList);
        cityList.setAdapter(listAdapter);
        cityList.setLayoutManager(new LinearLayoutManager(this));
    }
}