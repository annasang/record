package com.mercy.dona.hi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.support.v4.content.ContextCompat.startActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.concurrent.Executor;


public class BroadcastExample extends BroadcastReceiver {

   // private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    //private static final String TAG = "SmsBroadcastReceiver";
   // public String msg, phoneNo = "";
    //public boolean gpscheck, ringercheck, trustcheck;
    DataBaseHelper myDb ;
    //public static AudioManager am;
    private FusedLocationProviderClient client;
    double longitude,latitude;

  static Home ma; //a reference to activity's context

    public static void MA(Home maContext){
       ma=maContext;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        //retrieves the general action to be performed and display on log
   //     Log.i(TAG, "Intent Received: " +intent.getAction());
        myDb = new DataBaseHelper(context);
     //   am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
      //  Toast.makeText(context, "detected", Toast.LENGTH_LONG).show();


        client = LocationServices.getFusedLocationProviderClient(context);

      //  Toast.makeText(context, "detected", Toast.LENGTH_LONG).show();

        /*if (intent.getAction()==SMS_RECEIVED)
        {
            //retrieves a map of extended data from the intent
            Bundle dataBundle = intent.getExtras();
            if (dataBundle!=null)
            {
                //creating PDU(Protocol Data Unit) object which is a protocol for transferring message
                Object[] mypdu = (Object[])dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[mypdu.length];

                for (int i = 0; i<mypdu.length; i++)
                {
                    //for build versions >= API Level 23
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        String format = dataBundle.getString("format");
                        //From PDU we get all object and SmsMessage Object using following line of code
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i], format);
                    }
                    else
                    {
                        //<API level 23
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i]);
                    }
                    msg = message[i].getMessageBody();
                    phoneNo = message[i].getOriginatingAddress();
                }

              // Toast.makeText(context, phoneNo, Toast.LENGTH_LONG).show();




                   trustcheck = myDb.existData(phoneNo);
                   if(trustcheck) {
                      Toast.makeText(context, "got it", Toast.LENGTH_LONG).show();
                       final SmsManager sms = SmsManager.getDefault();
                       gpscheck = myDb.checkgps(phoneNo,msg);
                       ringercheck = myDb.checkringer(phoneNo,msg);



                       if(gpscheck){
                           //sms.sendTextMessage(phoneNo, null, "nothing", null, null);
                           if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                               // TODO: Consider calling
                               return;
                           }

                       //  sms.sendTextMessage(phoneNo, null, "nothing", null, null);
                           Toast.makeText(context, "outside", Toast.LENGTH_LONG).show();

                          client.getLastLocation().addOnSuccessListener(ma, new OnSuccessListener<Location>() {
                               @Override
                               public void onSuccess(Location location) {
                                   if(location != null) {
                                       longitude = location.getLongitude();
                                       latitude = location.getLatitude();
                                       String lat = Double.toString(latitude);
                                       String lon = Double.toString(longitude);
                                       String url = "http://maps.google.com/maps?q=loc:"+lat+","+lon;
                                       sms.sendTextMessage(phoneNo, null, url, null, null);
                                      // Toast.makeText(context, lat, Toast.LENGTH_LONG).show();
                                   }
                               }
                           });

                       }

                       if(ringercheck){
                           am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                           sms.sendTextMessage(phoneNo,null,"The phone is set to ringer mode. Call to locate it.",null,null);
                       }

                   }

            }
        }*/
    }
}
