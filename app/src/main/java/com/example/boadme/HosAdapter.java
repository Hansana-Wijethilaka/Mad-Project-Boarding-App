package com.example.boadme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HosAdapter extends ArrayAdapter<Booking> {

    private Context context;
    private int resource;
    List<Booking> hostals;

    HosAdapter(Context context, int resource, List<Booking> hostals) {
        super(context, resource, hostals);
        this.context = context;
        this.resource = resource;
        this.hostals = hostals;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView name = row.findViewById(R.id.view_name);
        TextView contac = row.findViewById(R.id.view_contact);
        TextView age = row.findViewById(R.id.view_age);
        TextView gend = row.findViewById(R.id.view_gender);


        Booking booking = hostals.get(position);
        name.setText(booking.getName());
        contac.setText(booking.getContact());
        age.setText(booking.getAge());
        gend.setText(booking.getGender());



        return row;

    }
}
