package com.example.roosevelt.notifications_lab;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder notificationBuilder;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationBuilder = new NotificationCompat.Builder(this);

        NotificationCompat.BigPictureStyle pictureStyle =
                new android.support.v4.app.NotificationCompat.BigPictureStyle();

        if (isConnected()){
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                    R.drawable.wireless_ok));
            intent = new Intent(this, SecondActivity.class);
            notificationBuilder.setAutoCancel(true)
                .setContentTitle("Connected!")
                .setSmallIcon(R.drawable.ic_wifi_black_24dp);
        }
        else {
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                    R.drawable.wirless_none));
            intent = new Intent(this, MainActivity.class);
            notificationBuilder.setAutoCancel(false)
                    .setContentTitle("Not Connected!")
                    .setSmallIcon(android.R.drawable.ic_dialog_alert);
        }

        notificationBuilder.setStyle(pictureStyle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 123, intent, 0);
        notificationBuilder.setContentIntent(pendingIntent);


        NotificationManagerCompat.from(MainActivity.this).notify(123, notificationBuilder.build());

    }

    boolean isConnected(){

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
