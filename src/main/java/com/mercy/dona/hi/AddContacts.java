package com.mercy.dona.hi;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AddContacts extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;

    Button btnAdd,button;
    DataBaseHelper myDb;
    EditText editnumber, editgps, editringer;
    ImageView myimg;
    TextView textView;
    private Uri file;
    //private final int PICK_CONTACT = 55;
    private FusedLocationProviderClient client;
    double longitude, latitude;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        myDb = new DataBaseHelper(this);

        //btnselect = (Button) findViewById(R.id.button_select);
       // editnumber = (EditText) findViewById(R.id.contacttext);
        editgps = (EditText) findViewById(R.id.gpstext);
        editringer = (EditText) findViewById(R.id.ringertext);
        btnAdd = (Button) findViewById(R.id.button_add);
        button=(Button)findViewById(R.id.ringer);
       // textView=(TextView)findViewById(R.id.textView4);
        // takepic=(Button) findViewById(R.id.button2);
        myimg = (ImageView) findViewById(R.id.imageView);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            myimg.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);


        }
        client = LocationServices.getFusedLocationProviderClient(this);
        button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              calendar = Calendar.getInstance();
                                              year = calendar.get(Calendar.YEAR);
                                              month = calendar.get(Calendar.MONTH);
                                              dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                                              datePickerDialog = new DatePickerDialog(AddContacts.this,
                                                      new DatePickerDialog.OnDateSetListener() {
                                                          @Override
                                                          public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                                              editringer.setText(day + "/" + (month + 1) + "/" + year);
                                                          }
                                                      }, year, month, dayOfMonth);
                                              datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                                              datePickerDialog.show();
                                          }
                                      });


        AddData();


       /* btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });*/


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                myimg.setEnabled(true);
            }
        }
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(intent, 100);
            if (ActivityCompat.checkSelfPermission(AddContacts.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Toast.makeText(AddContacts.this, "outside", Toast.LENGTH_LONG).show();
            client.getLastLocation().addOnSuccessListener(AddContacts.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        longitude = location.getLongitude();
                        latitude = location.getLatitude();
                        String lat = Double.toString(latitude);
                        String lon = Double.toString(longitude);
                        String url = "http://maps.google.com/maps?q=loc:" + lat + "," + lon;
                        Toast.makeText(AddContacts.this,lat+","+lon,Toast.LENGTH_LONG).show();
                       // sms.sendTextMessage(phoneNo, null, url, null, null);
                        // Toast.makeText(context, lat, Toast.LENGTH_LONG).show();
                       // textView.setText(Html.fromHtml(url));
                      //  textView.setMovementMethod(LinkMovementMethod.getInstance());

                    }

                }
            });
        }
    }
    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "hi");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("hi", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                myimg.setImageBitmap(imageBitmap);



            }
        }
    }


    ////////////////////////////// FOR SELECTING THE CONTACT ///////////////////////////////////

   // @Override
   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);

            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                // Do something with the phone number
                Toast.makeText(this, number, Toast.LENGTH_LONG).show();
                editnumber.setText(number);

            }

            cursor.close();
        }
    }*/



    public void AddData() {
        String timeStamp = new SimpleDateFormat("dd/MM/YYYY_HH:mm:ss").format(new Date());
        final String lat = Double.toString(latitude);
        final String lon = Double.toString(longitude);
        if (ActivityCompat.checkSelfPermission(AddContacts.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Toast.makeText(AddContacts.this, "outside", Toast.LENGTH_LONG).show();
        client.getLastLocation().addOnSuccessListener(AddContacts.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    final String lat = Double.toString(latitude);
                    final String lon = Double.toString(longitude);
                    String url = "http://maps.google.com/maps?q=loc:" + lat + "," + lon;
                    Toast.makeText(AddContacts.this,lat+","+lon,Toast.LENGTH_LONG).show();
                    // sms.sendTextMessage(phoneNo, null, url, null, null);
                    // Toast.makeText(context, lat, Toast.LENGTH_LONG).show();
                    //textView.setText(Html.fromHtml(url));
                   // textView.setMovementMethod(LinkMovementMethod.getInstance());
                    btnAdd.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {



                                        boolean inserted = myDb.insertData(editgps.getText().toString(), editringer.getText().toString(), imageViewtobyte(myimg), lat, lon);


                                        if (inserted == true) {
                                            Toast.makeText(AddContacts.this, "data inserted", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(AddContacts.this, "not", Toast.LENGTH_SHORT).show();
                                        }

                                   
                                }

                                private byte[] imageViewtobyte(ImageView image) {
                                    Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
                                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                    byte [] byteArray= stream.toByteArray();
                                    return byteArray;
                                }
                            }
                    );

                }

            }
        });



                    // sms.sendTextMessage(phoneNo, null, url, null, null);
                    // Toast.makeText(context, lat, Toast.LENGTH_LONG).show();






    }
}
