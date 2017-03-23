package com.jb.brunelbuysell.messagebrunel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView2;
    String message = "No New Messages";

    private TextView textView;
    private BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textViewToken);

        broadcastReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent){


                textView.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());

            }
        };

        if (SharedPrefManager.getInstance(this).getToken() != null) {
            textView.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            Log.d("myfcmtokenshared", SharedPrefManager.getInstance(this).getToken());
        }


        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));

        textView2 = (TextView)findViewById(R.id.textView2);

        if(getIntent().getExtras() != null)
        {
            message = getIntent().getExtras().getString("message");
            if(message==null)
            {
                message = "No New Messages";
            }
        }
        textView2.setText("Message: "+message);
    }
}
