package org.loecasi.android.feature.main.home

import android.content.Context
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import dagger.android.support.AndroidSupportInjection
import org.loecasi.android.R
import org.loecasi.android.toast
import javax.inject.Inject

/**
 * Created by dani on 10/30/17.
 */
class HomeFragment : Fragment(), HomeMvpView, OnMapReadyCallback {

    @Inject lateinit var presenter: HomeMvpPresenter<HomeMvpView>

    private var map: GoogleMap? = null
    private var defaultLocation: LatLng = LatLng(-2.27, 99.46)
    private var lastKnownLocation: Location? = null
    private var locationPermissionGranted: Boolean = false
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null

    private var locationRequestPermissions: OnLocationRequestPermission? = null
    private var locationChangeListener: OnLocationChangeListener? = null

    companion object {
        val LOG_TAG = HomeFragment::class.java.simpleName
        val DEFAULT_ZOOM = 15f
        val LAST_KNOWN_LOCATION_KEY = "LAST_KNOWN_LOCATION_KEY"

        fun newInstance(location: Location?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putParcelable(LAST_KNOWN_LOCATION_KEY, location)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        presenter.onAttach(this)

        if (context is OnLocationRequestPermission) {
            locationRequestPermissions = context
        } else {
            throw ClassCastException("${context.toString()} must implement " +
                    "OnLocationRequestPermission")
        }

        if (context is OnLocationChangeListener) {
            locationChangeListener = context
        } else {
            throw ClassCastException("${context.toString()} must implement " +
                    "OnLocationChangeListener")
        }

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lastKnownLocation = arguments.getParcelable(LAST_KNOWN_LOCATION_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

        val mapFragment = childFragmentManager.findFragmentById(R.id.fragment_map)
                as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onDetach() {
        presenter.onDetach()
        if (map != null) map = null
        if (fusedLocationProviderClient != null) fusedLocationProviderClient = null

        super.onDetach()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        map?.uiSettings?.isMapToolbarEnabled = false
        map?.uiSettings?.isRotateGesturesEnabled = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            locationRequestPermissions?.getLocationRequest()
        } else {
            onLocationPermissionGranted()
        }
    }

    fun onLocationPermissionGranted() {
        locationPermissionGranted = true
        updateLocationUI()

        if (lastKnownLocation != null) {
            lastKnownLocation?.let { moveCamera(LatLng(it.latitude, it.longitude)) }
        } else {
            getDeviceLocation()
        }
    }

    fun onLocationPermissionDenied() {
        activity.toast("Access location permission was denied")
    }

    private fun updateLocationUI() {
        if (map == null) return

        try {
            if (locationPermissionGranted) {
                map?.isMyLocationEnabled = true
                map?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                map?.isMyLocationEnabled = false
                map?.uiSettings?.isMyLocationButtonEnabled = false
            }
        } catch (e: SecurityException) {
            Log.e(LOG_TAG, "Update location UI error", e)
        }
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult: Task<Location> = fusedLocationProviderClient?.lastLocation!!
                locationResult.addOnCompleteListener(activity, {
                    if (it.isSuccessful) {
                        // Set the map's camera position to the current location of the device
                        lastKnownLocation = it.result
                        lastKnownLocation?.let {
                            val currentLatLng = LatLng(it.latitude, it.longitude)
                            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
                                    DEFAULT_ZOOM))

                            locationChangeListener?.saveLastKnownLocation(it)
                        }
                    } else {
                        map?.animateCamera(CameraUpdateFactory.newLatLng(defaultLocation))
                        map?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                })
            }
        } catch (e: SecurityException) {
            Log.e(LOG_TAG, e.message, e)
        }
    }

    private fun moveCamera(latLng: LatLng) {
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM))
    }

    interface OnLocationRequestPermission {
        fun getLocationRequest()
    }

    interface OnLocationChangeListener {
        fun saveLastKnownLocation(location: Location)
    }
}
