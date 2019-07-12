package com.mercy.dona.hi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class trusted_list_del extends AppCompatActivity {

    DataBaseHelper db;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_list);

        db = new DataBaseHelper(this);

        listItem = new ArrayList<>();
        userList = findViewById(R.id.users_list);
        viewData();

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String text = userList.getItemAtPosition(position).toString();
                Toast.makeText(trusted_list_del.this ,"added", Toast.LENGTH_SHORT).show();
                // add_name.setText("");
                //listItem.clear();
                //viewData();
                Intent intent = new Intent(trusted_list_del.this, DeleteContact.class);
                intent.putExtra("contactdel", text);
                startActivity(intent);
            }
        });

    }

    private void viewData() {
        Cursor cursor = db.viewData();

        if(cursor.getCount() ==0){
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
        }

        else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0));
                // listItem.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listItem);
            userList.setAdapter(adapter);
        }
    }
}
