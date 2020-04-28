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
import android.widget.Toast;

public class update extends AppCompatActivity {

    private EditText name2,  contact2, age2,gender2;
    //private RadioGroup gender2;
    private Button edit_button2;
    private DbHandler dbHandler;
    private Context context;
    private Long updateDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_);

        context = this;
        dbHandler = new DbHandler(context);

        name2 = findViewById(R.id.name_1);
        contact2 = findViewById(R.id.contact_);
        age2 = findViewById(R.id.age_1);
        gender2 = findViewById(R.id.gender01);

        edit_button2 = findViewById(R.id.btn_2);

        final String id = getIntent().getStringExtra("booking_id");
        Booking booking = dbHandler.getSingleBooking(Integer.parseInt(id));

        name2.setText(booking.getName());
        contact2.setText(booking.getContact());
        age2.setText(booking.getAge());
        gender2.setText(booking.getGender());
        // RadioButton checkBtn = findViewById(gender2.getCheckedRadioButtonId());
        //String edit_rad  = checkBtn.getText().toString();


        edit_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = getApplicationContext();
                CharSequence text = "Update Successfully";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


                String edit_name = name2.getText().toString();
                String edit_contact = contact2.getText().toString();
                String edit_age = age2.getText().toString();
                String edit_gender = gender2.getText().toString();
                //  RadioButton checkBtn = findViewById(gender2.getCheckedRadioButtonId());
                // String edit_rad  = checkBtn.getText().toString();

                updateDate = System.currentTimeMillis();

                Booking booking = new Booking(Integer.parseInt(id),edit_name,edit_contact,edit_age,edit_gender,updateDate,0);
                int state = dbHandler.update_Booking(booking);
                startActivity(new Intent(context,view.class));
            }
        });

    }
}


