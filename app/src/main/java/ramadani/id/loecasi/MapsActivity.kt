package ramadani.id.loecasi

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private var mMap: GoogleMap? = null

    companion object {
        val LOG_TAG = MapsActivity::class.java.simpleName
        val ACCESS_MY_LOCATION_PERMISSIONS_REQUEST = 10001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {

        if (requestCode == ACCESS_MY_LOCATION_PERMISSIONS_REQUEST) {
            if (permissions.size == 1 && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                mMap?.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "Access location permission was denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermissionToAccessFineLocation()
        }

        mMap?.uiSettings?.isMapToolbarEnabled = false
        mMap?.setOnMyLocationButtonClickListener(this)
        mMap?.setOnMyLocationClickListener(this)
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Log.d(LOG_TAG, "onMyLocationClick")
        Toast.makeText(
                this,
                "Current location LAT ${location.latitude} LONG ${location.longitude}",
                Toast.LENGTH_SHORT
        ).show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun getPermissionToAccessFineLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mMap?.isMyLocationEnabled = true
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    ACCESS_MY_LOCATION_PERMISSIONS_REQUEST)
        }
    }
}
