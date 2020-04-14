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

public class HostalAdapter extends ArrayAdapter<Hostal> {

    private Context context;
    private int resource;
    List<Hostal> hostals;

    HostalAdapter(Context context, int resource, List<Hostal> hostals){
        super(context, resource, hostals);
        this.context = context;
        this.resource = resource;
        this.hostals = hostals;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView owner_name = row.findViewById(R.id.view_owner_name);
        TextView ownerlocation = row.findViewById(R.id.view_hostal_location);
        TextView phonenum = row.findViewById(R.id.view_phonenum);
        TextView email = row.findViewById(R.id.view_email);
        TextView address = row.findViewById(R.id.view_address);
        TextView numofrm = row.findViewById(R.id.view_numofrm);
        TextView price = row.findViewById(R.id.view_price);

        ImageView imageView = row.findViewById(R.id.checkBox);

        Hostal hostal = hostals.get(position);
        owner_name.setText(hostal.getOwner_name());
        ownerlocation.setText(hostal.getHostal_location());
        phonenum.setText(hostal.getPhone_num());
        email.setText(hostal.getEmail());
        address.setText(hostal.getAddress());
        numofrm.setText(hostal.getNum_of_rm());
        price.setText(hostal.getPrice());
        imageView.setVisibility(row.INVISIBLE);

        if(hostal.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }

        return row;

    }
}
