package org.loecasi.android.feature.main.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
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
    private var locationRequestPermissions: OnLocationRequestPermission? = null
    private var locationPermissionGranted: Boolean = false

    companion object {
        val LOG_TAG = HomeFragment::class.java.simpleName
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

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.fragment_map)
                as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onDetach() {
        presenter.onDetach()
        if (map != null) map = null

        super.onDetach()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            locationRequestPermissions?.getLocationRequest()
        } else {
            locationPermissionGranted = true
        }

        map?.uiSettings?.isMapToolbarEnabled = false
        map?.uiSettings?.isRotateGesturesEnabled = false

        updateLocationUI()
    }

    fun onLocationPermissionGranted() {
        locationPermissionGranted = true
        updateLocationUI()
    }

    fun onLocationPermissionDenied() {
        activity.toast("Access location permission was denied")
    }

    private fun updateLocationUI() {
        map?.let {
            try {
                Log.d(LOG_TAG, "update location UI")
                if (locationPermissionGranted) {
                    Log.d(LOG_TAG, "locationPermissionGranted true")
                    map?.isMyLocationEnabled = true
                    map?.uiSettings?.isMyLocationButtonEnabled = true
                } else {
                    Log.d(LOG_TAG, "locationPermissionGranted false")
                    map?.isMyLocationEnabled = false
                    map?.uiSettings?.isMyLocationButtonEnabled = false
                }
            } catch (e: SecurityException) {
                Log.e(LOG_TAG, "Update location UI error", e)
            }
        }
    }

    interface OnLocationRequestPermission {

        fun getLocationRequest()
    }
}
