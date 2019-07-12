package com.mercy.dona.hi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DeleteContact extends AppCompatActivity {

    EditText contact;
    Button openlist;
    Button deleteData1;
    DataBaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        openlist = (Button) findViewById(R.id.openDel);

        contact = (EditText) findViewById(R.id.selDel);
        deleteData1 = (Button) findViewById(R.id.del);
        myDb = new DataBaseHelper(this);

        DeleteData();
        OpenList();

        Intent intent = getIntent();
        String select = intent.getStringExtra("contactdel");

        contact.setText(select);


    }

  @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Intent intent1 = new Intent(this,Home.class);
        startActivity(intent1);
    }

    public void OpenList(){
        openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DeleteContact.this, trusted_list_del.class);
                startActivity(intent1);
            }
        });
    }

    public void DeleteData(){
        deleteData1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRow = myDb.deletedata(contact.getText().toString());
                        if (deleteRow > 0 )
                            Toast.makeText(DeleteContact.this, "data deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DeleteContact.this, "not deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}
