package com.mercy.dona.hi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageContact extends AppCompatActivity {

    Button openlist,updateData1;
    EditText contact, gpscode,ringercode,wht;
    DataBaseHelper myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_contact);


        openlist = (Button)findViewById(R.id.open);
        updateData1 =(Button)findViewById(R.id.updatecon);

        contact = (EditText) findViewById(R.id.selDel);
        gpscode =(EditText) findViewById(R.id.gps);
        ringercode = (EditText) findViewById(R.id.ringer);

        myDb = new DataBaseHelper(this );

        Intent intent = getIntent();
        String select = intent.getStringExtra("contact");

        contact.setText(select);

        updateData();


        openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageContact.this, trusted_list.class);
                startActivity(intent);
            }
        });

    }

    public void updateData() {
        updateData1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(contact.getText().toString(),
                                gpscode.getText().toString(),ringercode.getText().toString());

                        if (isUpdate == true)
                            Toast.makeText(ManageContact.this, "data updated", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(ManageContact.this, "not updated", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Intent intent1 = new Intent(this,Home.class);
        startActivity(intent1);
    }
}
