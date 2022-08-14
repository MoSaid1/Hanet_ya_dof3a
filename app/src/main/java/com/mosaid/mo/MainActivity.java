package com.mosaid.mo;
import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import static java.time.temporal.ChronoUnit.DAYS;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {
    Button bb;
    String month="00" , year="00";
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView2;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);




        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3816079514483358/8207090014", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });




















        String[] dof3a = new String[]{"3","6","9","12"};
        String[] years = new String[]{"2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                dof3a
        );
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                years
        );
        autoCompleteTextView = findViewById(R.id.filled_exposed0);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,autoCompleteTextView.getText().toString(),Toast.LENGTH_SHORT).show();
                month = autoCompleteTextView.getText().toString();
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
            }
        });
        autoCompleteTextView2 = findViewById(R.id.filled_exposed);
        autoCompleteTextView2.setAdapter(adapter2);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,autoCompleteTextView2.getText().toString(),Toast.LENGTH_SHORT).show();
                year = autoCompleteTextView2.getText().toString();

            }
        });



        bb  = (Button)findViewById(R.id.calc);
        bb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (year == "00" || month == "00"){
                    Toast.makeText(MainActivity.this,"اختار دفعتك عدل يا كحول".toString(),Toast.LENGTH_LONG).show();
                }else {

                    Intent n = new Intent(MainActivity.this,View_date.class);
                    n.putExtra("data1",month);
                    n.putExtra("data2",year);

                    startActivity(n);}

            }
        });



    }

    }
