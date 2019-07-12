package com.mercy.dona.hi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Bookingsfragment extends Fragment {

    Button open1,open2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_sec,container,false);
        View view=inflater.inflate(R.layout.fragment_sec,container,false);


        open1 = (Button)view.findViewById(R.id.update);
        open2 = (Button) view.findViewById(R.id.delete1);

        open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "dasdas", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ManageContact.class);
                startActivity(intent);

            }
        });

        open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),DeleteContact.class);
                startActivity(intent);
            }
        });


        return view;
    }



}
