package com.mosaid.mo;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.YEARS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class View_date extends AppCompatActivity {
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;
    TextView  vDay , vHour , vMin;
    int month,year;
    private AdView mAdView;


    String day,years,hours,min;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_date);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);







            Intent intent = getIntent();
        month =Integer.valueOf( intent.getStringExtra("data1"));
        year = Integer.valueOf(intent.getStringExtra("data2"));


        vDay = (TextView)findViewById(R.id.day);
        vHour = (TextView)findViewById(R.id.hours);
        vMin = (TextView)findViewById(R.id.min);




        long yourmilliseconds = System.currentTimeMillis();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime n =  LocalDateTime.of(year,month-1,25,00,00,00);



        long lday = DAYS.between(now,n);
        long lyear = YEARS.between(now,n);
        long lhours = HOURS.between(now,n);
        long lmin = MINUTES.between(now,n);

        day = Long.toString(lday);
        years  = Long.toString(lyear);
        hours  = Long.toString(lhours-(lday*24));
        min = Long.toString(lmin-(lhours)*60);



        vDay.setText(day);
        vHour.setText(hours);
        vMin.setText(min);



        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("01","notif", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);}

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"01")
                .setContentTitle("هانت ياكحول")
                .setContentText("فاضلك"+day+"يوم و"+hours+"ساعة و"+min+"دقيقة")
                .setSmallIcon(R.drawable.logo_icon);





        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);


        notificationManagerCompat.notify(1,notification);
    }



}
