package com.example.boadme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Hostal_listviewActivity extends AppCompatActivity {

    private Button add;
    private ListView listview;
    private TextView count;
    Context context;
    private DbHandler dbHandler;
    private List<Hostal> hostalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostal_listview);

        context = this;
        dbHandler = new DbHandler(context);
        add = findViewById(R.id.add);

        listview = findViewById(R.id.hostal_list);
        count = findViewById(R.id.hostal_count);

        hostalList = new ArrayList<>();
        hostalList = dbHandler.getAllHostal();

        HostalAdapter hostalAdapter = new HostalAdapter(context,R.layout.singale_hostal,hostalList);

        listview.setAdapter(hostalAdapter);

        int count_Hostal = dbHandler.countHostal();
        count.setText("You have "+count_Hostal+ " Hostal");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,Addhostal.class));
            }
        });

    }
}
