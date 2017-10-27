package org.loecasi.android.feature.main

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import dagger.android.AndroidInjection
import org.loecasi.android.R
import javax.inject.Inject

class MainActivity : FragmentActivity(), MainMvpView, OnMapReadyCallback {

    @Inject lateinit var presenter: MainMvpPresenter<MainMvpView>

    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mTvLat: TextView
    private lateinit var mTvLng: TextView

    private var mLastKnownLocation: Location? = null
    private var mMap: GoogleMap? = null

    private var mDefaultLocation = LatLng(-2.27, 99.46)
    private var mLocationPermissionGranted: Boolean = false

    companion object {
        val LOG_TAG = MainActivity::class.java.simpleName
        val ACCESS_MY_LOCATION_PERMISSIONS_REQUEST = 10001
        val DEFAULT_ZOOM = 17f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        presenter.onAttach(this)

        mTvLat = findViewById(R.id.tv_lat)
        mTvLng = findViewById(R.id.tv_lng)

        // Construct a FusedLocationProviderClient
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {

        if (requestCode == ACCESS_MY_LOCATION_PERMISSIONS_REQUEST) {
            if (permissions.size == 1 && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                mLocationPermissionGranted = true
                updateLocationUI()
                getDeviceLocation()
            } else {
                Toast.makeText(this, "Access location permission was denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getLocationPermission()
        } else {
            mLocationPermissionGranted = true
        }

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI()

        // Get the current location of the device and set position of the map.
        getDeviceLocation()

        mMap?.uiSettings?.isMapToolbarEnabled = false
        mMap?.uiSettings?.isRotateGesturesEnabled = false
        mMap?.setOnCameraIdleListener {
            val latLng = mMap?.cameraPosition?.target
            latLng?.let { setLocationData(it) }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    ACCESS_MY_LOCATION_PERMISSIONS_REQUEST)
        }
    }

    private fun updateLocationUI() {
        if (mMap == null) return

        try {
            if (mLocationPermissionGranted) {
                mMap?.isMyLocationEnabled = true
                mMap?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                mMap?.isMyLocationEnabled = false
                mMap?.uiSettings?.isMyLocationButtonEnabled = false
                mLastKnownLocation = null
            }
        } catch (e: SecurityException) {
            Log.e(LOG_TAG, e.message, e)
        }
    }

    private fun getDeviceLocation() {
        /**
         * Get the best and most recent location of the device, which may beh null in rare
         * cases when a location is not available
         */

        try {
            if (mLocationPermissionGranted) {
                val locationResult: Task<Location> = mFusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(this, {
                    if (it.isSuccessful) {
                        // Set the map's camera position to the current location of the device
                        mLastKnownLocation = it.result
                        mLastKnownLocation?.let {
                            val currentLatLng = LatLng(it.latitude, it.longitude)
                            mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
                                    DEFAULT_ZOOM))
                        }
                    } else {
                        Log.d(LOG_TAG, "Current location is null. Using defaults.")
                        Log.e(LOG_TAG, "Exception ${it.exception?.message}", it.exception)

                        mMap?.animateCamera(CameraUpdateFactory.newLatLng(mDefaultLocation))
                        mMap?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                })
            }
        } catch (e: SecurityException) {
            Log.e(LOG_TAG, e.message, e)
        }
    }

    private fun setLocationData(latLng: LatLng) {
        mTvLat.text = getString(R.string.latitude, latLng.latitude.toString())
        mTvLng.text = getString(R.string.longitude, latLng.longitude.toString())
    }
}
