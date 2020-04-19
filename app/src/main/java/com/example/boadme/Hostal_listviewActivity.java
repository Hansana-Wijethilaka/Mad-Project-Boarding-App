package com.example.boadme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Hostal hostal = hostalList.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle(hostal.getOwner_name());
                builder.setMessage(hostal.getHostal_location());


                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,Hostal_listviewActivity.class));
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteHostal(hostal.getId());
                        startActivity(new Intent(context,Hostal_listviewActivity.class));
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,Edit_hostalActivity.class));
                    }
                });

                builder.show();
            }
        });

    }
}
