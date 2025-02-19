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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    protected String getCity() {
        //buggy method, fix the bug! Hints: Check DetailsActivity
        return null;
    }

    protected LatLng getLatLong() {
        return new LatLng(AppConstants.LATITUDE, AppConstants.LONGITUDE);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //buggy method, fix the bug!
        this.map = googleMap;
        map.setMaxZoomPreference(5);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 20));
    }
}