package com.example.boadme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Add extends AppCompatActivity {
    EditText name1, contact1, age1,gender1;
    Button add_button;

    private Context context;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name1 = findViewById(R.id.Full_Name);
        contact1 = findViewById(R.id.Contact_Details);
        age1 = findViewById(R.id.Age);
        gender1 = findViewById(R.id.Gender02);



        add_button = findViewById(R.id.btn1);

        context = this;
        dbHandler = new DbHandler(context);

        add_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_name = name1.getText().toString();
                String user_contact = contact1.getText().toString();
                String user_age = age1.getText().toString();
                String user_gender = gender1.getText().toString();
                //      RadioButton checkBtn = findViewById(gender1.getCheckedRadioButtonId());
                //   String gender2  = checkBtn.getText().toString();
                long started = System.currentTimeMillis();

                Hostal hostal = new Hostal(user_name,user_contact,user_age,user_gender,started,0);
                dbHandler.add_Hostal(hostal);

                startActivity(new Intent(context,view.class));


            }
        });

    }
}