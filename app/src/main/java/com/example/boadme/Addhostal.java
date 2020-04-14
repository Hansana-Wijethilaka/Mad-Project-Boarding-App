package com.example.boadme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Addhostal extends AppCompatActivity {

    private EditText edit_ownername, edit_hostallocation, edit_phonenum, edit_email, edit_address, edit_numofrm, edit_price;
    private Button add_button;

    private Context context;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhostal);

        edit_ownername = findViewById(R.id.edit_owner_name);
        edit_hostallocation = findViewById(R.id.edit_hostal_location);
        edit_phonenum = findViewById(R.id.edit_phone_num);
        edit_email = findViewById(R.id.edit_owner_email);
        edit_address = findViewById(R.id.edit_address);
        edit_numofrm = findViewById(R.id.edit_room);
        edit_price = findViewById(R.id.edit_price);

        add_button = findViewById(R.id.addhostal);

        context = this;
        dbHandler = new DbHandler(context);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ownername = edit_ownername.getText().toString();
                String hostallocation = edit_hostallocation.getText().toString();
                String phonenum = edit_phonenum.getText().toString();
                String email = edit_email.getText().toString();
                String address = edit_address.getText().toString();
                String numofrm = edit_numofrm.getText().toString();
                String price = edit_price.getText().toString();

                long started = System.currentTimeMillis();

                Hostal hostal = new Hostal(ownername,hostallocation,phonenum,email,address,numofrm,price,started,0);
                dbHandler.add_Hostal(hostal);

                startActivity(new Intent(context,Hostal_listviewActivity.class));

            }
        });

    }
}
