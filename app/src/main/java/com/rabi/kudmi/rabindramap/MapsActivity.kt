package com.rabi.kudmi.rabindramap

import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rabi.kudmi.rabindramap.databinding.ActivityMapsBinding
import java.io.IOException

class MapsActivity : AppCompatActivity(),OnMapReadyCallback {

     var mMap: GoogleMap?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        setTitle("Rabindra Map")
        val mapFragment =  supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment;
         mapFragment.getMapAsync(this);

    }

    override fun onMapReady(googleMap : GoogleMap) {
        mMap = googleMap;
        val myLatLng  = LatLng(12.951087786423512,77.58530781135241);
        val markerOptions = MarkerOptions().position(myLatLng).title("LALBAGH");
        mMap?.addMarker(markerOptions);
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng,16f));

        mMap?.addCircle(CircleOptions().center(myLatLng).radius(100.0).fillColor(Color.BLUE).strokeColor(Color.BLACK));

        mMap?.setOnMapClickListener(object :OnMapClickListener
        {
            override fun onMapClick(latLng: LatLng) {
                   mMap?.clear();
                val markerOptions = MarkerOptions().position(latLng);
                mMap?.addMarker(markerOptions);

                val geocoder = Geocoder(this@MapsActivity);
                try {
                    val arrAdd  = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1)
                      Toast.makeText(this@MapsActivity,"lat Long is"+latLng,Toast.LENGTH_SHORT).show();
                    Log.d("Address","data->"+arrAdd?.get(0)?.getAddressLine(0));

                }
                catch (e:IOException)
                {

                }
            }

        })




    }


}