package com.example.applicazionesaferide;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
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
import com.google.android.gms.location.LocationServices;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Allarme_Maps extends FragmentActivity implements OnMapReadyCallback {

    public  static final int PERMISSIONS_REQUEST_BT = 6;
    public  static final int PERMISSIONS_REQUEST_LOCATION = 123;
    int LOCATION_REFRESH_TIME = 15000; // 15 seconds to update
    int LOCATION_REFRESH_DISTANCE = 500; // 500 meters to update
    private GoogleMap mMap;
    private ActivityAllarmeMapsBinding binding;
    Location lastLocation;
    boolean locationPermissionGranted;


    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_LOCATION);
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        if (requestCode
                == PERMISSIONS_REQUEST_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                lastLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_allarme_maps);


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

        // ...

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();


    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            FusedLocationProviderClient fusedLocationClient =  LocationServices.getFusedLocationProviderClient(this);
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastLocation = task.getResult();
                            if (lastLocation != null) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastLocation.getLatitude(),
                                                lastLocation.getLongitude()), 30));
                            }

                            MarkerOptions markerOptions = new MarkerOptions();

                            LatLng latLng = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());

                            // Setting the position for the marker
                            markerOptions.position(latLng);

                            // Setting the title for the marker.
                            // This will be displayed on taping the marker
                            markerOptions.title(lastLocation.getLatitude() + " : " + lastLocation.getLongitude());

                            // Clears the previously touched position
                            mMap.clear();

                            // Animating to the touched position
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                            // Placing a marker on the touched position
                            mMap.addMarker(markerOptions);




                        } else {
                            Log.d("A", "Current location is null. Using defaults.");
                            Log.e("B", "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(new LatLng(100, 100), 100));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }




    public void startChronometer() {
        ((Chronometer) findViewById(R.id.cronometroTempo)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) findViewById(R.id.cronometroTempo)).start();
    }

    public void stopChronometer() {
        ((Chronometer) findViewById(R.id.cronometroTempo)).stop();

    }
}