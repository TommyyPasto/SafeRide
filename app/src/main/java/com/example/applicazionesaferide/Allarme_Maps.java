package com.example.applicazionesaferide;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.applicazionesaferide.databinding.ActivityAllarmeMapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.BreakIterator;
import java.util.Locale;

public class Allarme_Maps extends FragmentActivity implements OnMapReadyCallback {


    int LOCATION_REFRESH_TIME = 15000; // 15 seconds to update
    int LOCATION_REFRESH_DISTANCE = 500; // 500 meters to update
    private GoogleMap mMap;
    private ActivityAllarmeMapsBinding binding;
    Location mLastLocation;


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            mMap.clear();
            final LatLng loc = new LatLng(location.getLongitude(), location.getLongitude());

            Marker ham = mMap.addMarker(new MarkerOptions().position(loc).title("This is Me"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
        }
    };

/*
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        // use latitude and longitude given by
        // location.getLatitude(), location.getLongitude()
        // for updated location marker
        Log.d("aaaaaaaa===>", "" + location.getLatitude() + "\n" + location.getLongitude());
        // displayLocation();

        // to remove old markers
        mMap.clear();
        final LatLng loc = new LatLng(location.getLongitude(), location.getLongitude());

        Marker ham = mMap.addMarker(new MarkerOptions().position(loc).title("This is Me"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
    }*/


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAllarmeMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ((Chronometer) findViewById(R.id.cronometroTempo)).setFormat("Time %h:%m:%s");

        Button startBtn = (Button) findViewById(R.id.ImpostaAllarme);
        Button stopBtn = (Button) findViewById(R.id.StopAllarme);

        ImageView campanellaImg = findViewById(R.id.campanellaAlarm);
        ImageView stopImg = findViewById(R.id.stopSimbolo);

        TextView tempoTV = findViewById(R.id.tempoTrascorso);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChronometer();
                campanellaImg.setColorFilter(Color.WHITE);
                startBtn.setBackgroundResource(R.drawable.start_impostato);
                startBtn.setTextColor(Color.WHITE);
                startBtn.setText("ALLARME IMPOSTATO");
                stopImg.setColorFilter(Color.WHITE);
                stopBtn.setBackgroundResource(R.drawable.stop_impostato);
                stopBtn.setTextColor(Color.WHITE);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChronometer();
                campanellaImg.setColorFilter(Color.parseColor("#0016E2"));
                startBtn.setBackgroundResource(R.drawable.impostazioni_casella);
                startBtn.setTextColor(Color.parseColor("#0016E2"));
                startBtn.setText("IMPOSTA ALLARME");
                stopImg.setColorFilter(Color.parseColor("#0016E2"));
                stopBtn.setBackgroundResource(R.drawable.impostazioni_casella);
                stopBtn.setTextColor(Color.parseColor("#0016E2"));
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.

            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("MapsActivityRaw", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivityRaw", "Can't find style.", e);
        }
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(43.776366, 11.247822);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }



    public void startChronometer() {
        ((Chronometer) findViewById(R.id.cronometroTempo)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) findViewById(R.id.cronometroTempo)).start();
    }

    public void stopChronometer() {
        ((Chronometer) findViewById(R.id.cronometroTempo)).stop();

    }
}