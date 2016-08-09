package com.example.roosevelt.notifications_lab;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:13477491515"));
        Intent intent = new Intent(this, this.getClass());

        PendingIntent pendingCallIntent = PendingIntent.getActivity(this,
                456, callIntent, 0);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                789, intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(android.R.drawable.ic_menu_call)
                .setContentTitle("Speak to the Developer")
                .setContentText("Jae Anonas")
                .addAction(android.R.drawable.sym_action_call, "Call Jae", pendingCallIntent)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat.from(this).notify(111, notificationBuilder.build());
    }

}
