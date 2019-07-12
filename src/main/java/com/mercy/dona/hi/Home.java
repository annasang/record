package com.mercy.dona.hi;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

//import static java.security.AccessController.getContext;
//import static maes.tech.intentanim.CustomIntent.customType;

public class Home extends AppCompatActivity {


    DataBaseHelper myDb;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    public static AudioManager AUDIOMANAGER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BroadcastExample.MA(Home.this);

        Log.w("here","dasdsad");

        if(checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }

        myDb = new DataBaseHelper(this);


        BottomNavigationView bottomNavigationView=findViewById(R.id.btm_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fraglayout,new homefragment()).commit();

      /* NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()){

            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(Home.this, AddContacts.class);
                startActivity(intent);
                // Toast.makeText(MainActivity.this, sec.dumpy,Toast.LENGTH_SHORT).show();
            }
        });


    }


    private  boolean checkAndRequestPermissions() {
        //int permissionSendMessage = ContextCompat.checkSelfPermission(this,
             //   android.Manifest.permission.SEND_SMS);
        //int permissionReceivcemessage = ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS);
        int locationPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int internetPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        //if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
        //    listPermissionsNeeded.add(android.Manifest.permission.SEND_SMS);
        //}

        //if (permissionReceivcemessage != PackageManager.PERMISSION_GRANTED){
         //   listPermissionsNeeded.add(android.Manifest.permission.RECEIVE_SMS);
       // }
        if (internetPermission != PackageManager.PERMISSION_GRANTED){
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment fragment=null;
                    switch (menuItem.getItemId()){
                        case R.id.home:
                            fragment=new homefragment();
                            break;
                       case R.id.book:
                            fragment=new Bookingsfragment();
                            break;
                        case R.id.profile:
                            fragment=new ProfileFragment();
                            break;
                    }
                    Log.w("here","frag");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fraglayout,fragment).commit();
                    return true;
                }
            };


}
