package com.mercy.dona.hi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;


public class homefragment extends Fragment {

    DataBaseHelper db;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView userList;
    static  Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_homefragment,container,false);


        db = new DataBaseHelper(getContext());

        listItem = new ArrayList<String>();
        userList = view.findViewById(R.id.users_list1);

       // viewData();

        listItem.clear();
        userList.setAdapter(null);


        viewData1();
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userList.getItemAtPosition(position).toString();
                Intent intent= new Intent(view.getContext(),display.class);
                intent.putExtra("KEY",text);
                startActivityForResult(intent, 0);
              //  Toast.makeText(view.getContext(),text,Toast.LENGTH_LONG).show();
            }
        });





        return view;
    }

    private void viewData1() {

        cursor = db.viewData();

       // StringBuffer buffer = new StringBuffer();


        if(cursor.getCount() ==0){
            Toast.makeText(getContext(),"no data", Toast.LENGTH_SHORT).show();
        }

        else{



            while (cursor.moveToNext()) {

                //listItem.clear();
                //buffer.append("ID :" + cursor.getString(0) + "\n");
                //buffer.append("Scheme Name :" + cursor.getString(1) + "\n");
                //buffer.append("Date :" + cursor.getString(2) + "\n\n");
               // buffer.append("Latitute:"+cursor.getString(4)+"\n\n");
                //buffer.append("Longitute:"+cursor.getString(5)+"\n\n");

                listItem.add(cursor.getString(1));
            }
            }


            adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,listItem);
            userList.setAdapter(adapter);



           // listItem.clear();
        }



    }



