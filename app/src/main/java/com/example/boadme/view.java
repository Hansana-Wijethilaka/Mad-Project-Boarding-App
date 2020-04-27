package com.example.boadme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class view extends AppCompatActivity {

    private Button add;
    private ListView listView;
    Context context;
    private DbHandler dbHandler;
    private List<Hostal> hostalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        context = this;
        dbHandler = new DbHandler(context);

        add = findViewById(R.id.add1);
        listView = findViewById(R.id.view);

        hostalList = new ArrayList<>();
        hostalList = dbHandler.getAllHostal();


        HosAdapter hostalAdapter = new HosAdapter(context,R.layout.activity_list,hostalList);

        listView.setAdapter(hostalAdapter);

       /* int count_Hostal = dbHandler.countHostal();
        count.setText("Available "+count_Hostal+ " Hostal");
        */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,Add.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Hostal hostal = hostalList.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);


                builder.setTitle(hostal.getName());
                builder.setMessage(hostal.getContact());
                //builder.setMessage(hostal.getAge());
                //builder.setMessage(hostal.getGender());



                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,view.class));
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteHostal(hostal.getId());
                        startActivity(new Intent(context,view.class));
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, update.class);
                        intent.putExtra("id", String.valueOf(hostal.getId()));

                        startActivity(intent);


                    }
                });

                builder.show();
            }
        });

    }
}
