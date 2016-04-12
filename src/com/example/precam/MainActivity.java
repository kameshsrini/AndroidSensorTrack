package com.example.precam;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {

    Button btnRegId;
    EditText etRegId;
    GoogleCloudMessaging gcm;
    String regid;
    String PROJECT_NUMBER = "396789441275";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegId = (Button) findViewById(R.id.registerButton);
        etRegId = (EditText) findViewById(R.id.regId);

        btnRegId.setOnClickListener(this);
        /*Intent intent = getIntent();
        String extra = intent.getStringExtra("notification");

        if(extra != null && extra.equals("true") && (intent.getFlags() & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) == 0)
        {
            banner.animateOpen();
        }*/
        
        	//PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
    }
    
    public void getRegId(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM",  msg);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                etRegId.setText(msg + "\n");
            }
        }.execute(null, null, null);
    }
    @Override
    public void onClick(View v) {
        getRegId();
    } }