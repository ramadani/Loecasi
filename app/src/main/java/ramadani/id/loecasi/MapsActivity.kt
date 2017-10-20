package ramadani.id.loecasi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private var mMap: GoogleMap? = null

    companion object {
        val LOG_TAG = MapsActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(-7.79,110.366)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney, 16f)
        val mapMarker = MarkerOptions().position(sydney).title("Yogyakarta")
        mapMarker.draggable(true)

        mMap!!.addMarker(mapMarker)
        mMap!!.animateCamera(cameraUpdate)
        mMap!!.setOnMarkerDragListener(this)
    }

    override fun onMarkerDragStart(marker: Marker?) {

    }

    override fun onMarkerDrag(marker: Marker?) {

    }

    override fun onMarkerDragEnd(marker: Marker?) {
        Log.d(LOG_TAG, "Position ${marker?.position}")
    }
}
