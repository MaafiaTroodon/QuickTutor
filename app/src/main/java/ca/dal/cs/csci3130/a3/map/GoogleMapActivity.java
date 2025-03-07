package ca.dal.cs.csci3130.a3.map;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import ca.dal.cs.csci3130.a3.R;
import ca.dal.cs.csci3130.a3.config.AppConstants;

public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback {


    String city;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        this.city = getCity();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    protected String getCity() {
        return getIntent().getStringExtra("itemLocation");  // Extract city from intent
    }


    protected LatLng getLatLong() {
        return new LatLng(AppConstants.LATITUDE, AppConstants.LONGITUDE);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;

        // Get the correct latitude and longitude
        LatLng location = getLatLong();

        // Move the camera to the correct location with proper zoom level
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));

        // Enable zoom controls
        map.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker for the item location
        map.addMarker(new com.google.android.gms.maps.model.MarkerOptions().position(location).title(city));
    }

}