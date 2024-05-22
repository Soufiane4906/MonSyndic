package com.example.monsyndic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps_Activity extends AppCompatActivity  implements OnMapReadyCallback {
    MapView mapView1;
    private GoogleMap map;
    private  LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapView1=findViewById(R.id.mapView);
        mapView1.onCreate(savedInstanceState);
        mapView1.getMapAsync(this);

    }
    @Override
    public void onMapReady (@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng place = new LatLng(33.59137906564503, -7.604779917393251);
        map.addMarker(new MarkerOptions().position(place).title("Emsi Centre"));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 12));
    }}
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
//                        !=PackageManager.PERMISSION_GRANTED){
//                        ActivityCompat.requestPermissions
//                    (this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION));
//             return;
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0this);
//
//    }
//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//        map.clear();
//// Add a marker for the current location
//        LatLng currentLocation = new LatLng(location.getLatitude(),
//                location.getLongitude());
//        map.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
//// Move camera to the current location
//                map.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
//        map.animateCamera(CameraUpdateFactory.zoomTo(15));
//    }}