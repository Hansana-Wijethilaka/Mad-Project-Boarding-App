package com.example.boadme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit_hostalActivity extends AppCompatActivity {

    private EditText ownername, hostallocation, phonenum, email, address, numofrm, price;
    private Button edit_button;
    private DbHandler dbHandler;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hostal);

        context = this;
        dbHandler = new DbHandler(context);

        ownername = findViewById(R.id.edit_editowner_name);
        hostallocation = findViewById(R.id.edit_edithostal_location);
        phonenum = findViewById(R.id.edit_editphone_num);
        email = findViewById(R.id.edit_editowner_email);
        address = findViewById(R.id.edit_editaddress);
        numofrm = findViewById(R.id.edit_editroom);
        price = findViewById(R.id.edit_editprice);

        edit_button = findViewById(R.id.edithostal);

        final String id = getIntent().getStringExtra("id");
        Hostal hostal = dbHandler.getSingaleHostal(Integer.parseInt(id));

        ownername.setText(hostal.getOwner_name());
        hostallocation.setText(hostal.getHostal_location());
        phonenum.setText(hostal.getPhone_num());
        email.setText(hostal.getEmail());
        address.setText(hostal.getAddress());
        numofrm.setText(hostal.getNum_of_rm());
        price.setText(hostal.getPrice());

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edit_ownername = ownername.getText().toString();
                String edit_hostallocation = hostallocation.getText().toString();
                String edit_phonenum = phonenum.getText().toString();
                String edit_email = email.getText().toString();
                String edit_address = address.getText().toString();
                String edit_numofrm = numofrm.getText().toString();
                String edit_price = price.getText().toString();

                updateDate = System.currentTimeMillis();

                Hostal hostal = new Hostal(Integer.parseInt(id),edit_ownername,edit_hostallocation,edit_phonenum,edit_email,edit_address,edit_numofrm,edit_price,updateDate,0);
                int state = dbHandler.updateHostal(hostal);
                startActivity(new Intent(context,Hostal_listviewActivity.class));
            }
        });

    }
}
