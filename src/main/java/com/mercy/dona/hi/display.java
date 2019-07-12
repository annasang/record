package com.mercy.dona.hi;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class display extends AppCompatActivity {
    ImageView imageView;
    TextView textView,text1,text2,text3;
    DataBaseHelper mydb;
    static Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        textView=(TextView) findViewById(R.id.textView5);
        imageView=(ImageView)findViewById(R.id.imageView2);
        text1=(TextView) findViewById(R.id.textView13) ;
        text2=(TextView) findViewById(R.id.textView12) ;
        text3=(TextView) findViewById(R.id.textView6) ;
        mydb = new DataBaseHelper(this);
        Intent intent = getIntent();
        String select = intent.getStringExtra("KEY");
       // Toast.makeText(display.this,select,Toast.LENGTH_LONG).show();
        cursor = mydb.dis(select);
      //  Toast.makeText(display.this,cursor.getString(1),Toast.LENGTH_LONG).show();
        if(cursor.getCount() ==0){
            Toast.makeText(display.this,"no data", Toast.LENGTH_SHORT).show();
        }

        else{



            while (cursor.moveToNext()) {

                //listItem.clear();
                //buffer.append("ID :" + cursor.getString(0) + "\n");
                //buffer.append("Scheme Name :" + cursor.getString(1) + "\n");
                //buffer.append("Date :" + cursor.getString(2) + "\n\n");
                // buffer.append("Latitute:"+cursor.getString(4)+"\n\n");
                //buffer.append("Longitute:"+cursor.getString(5)+"\n\n");

                text1.setText("ID:"+cursor.getString(0));
                text2.setText("SCHEME NAME:"+cursor.getString(1));
                text3.setText("DATE:"+cursor.getString(2));
                imageView.setImageBitmap(getImageFromBLOB(cursor.getBlob(3)));
                String url= "http://maps.google.com/maps?q=loc:" + cursor.getString(4) + "," + cursor.getString(5);
                textView.setText(Html.fromHtml(url));
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }


    }


    public static Bitmap getImageFromBLOB(byte[] mBlob)
    {
        byte[] bb = mBlob;
        return BitmapFactory.decodeByteArray(bb, 0, bb.length);
    }


}
