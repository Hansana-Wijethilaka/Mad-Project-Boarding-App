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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class view extends AppCompatActivity {

    private Button add;
    private ListView listView;
    Context context;
    private DbHandler dbHandler;
    private List<Booking> hosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        context = this;
        dbHandler = new DbHandler(context);

        add = findViewById(R.id.add1);
        listView = findViewById(R.id.view);

        hosList = new ArrayList<>();
        hosList = dbHandler.getAllBooking();


        HosAdapter hosAdapter = new HosAdapter(context,R.layout.activity_list,hosList);

        listView.setAdapter(hosAdapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,Add.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Booking booking = hosList.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);


                builder.setTitle(booking.getName());
                builder.setMessage(booking.getContact());




                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,view.class));
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.delete_Booking(booking.getId());
                        startActivity(new Intent(context,view.class));

                        Context context = getApplicationContext();
                        CharSequence text = "Delete Successfully";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, update.class);
                        intent.putExtra("booking_id", String.valueOf(booking.getId()));

                        startActivity(intent);


                    }
                });

                builder.show();
            }
        });

    }
}
