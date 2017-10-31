package org.loecasi.android.feature.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import org.loecasi.android.R
import org.loecasi.android.feature.main.account.AccountFragment
import org.loecasi.android.feature.main.gift.GiftFragment
import org.loecasi.android.feature.main.home.HomeFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, MainMvpView,
        BottomNavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnLocationRequestPermission,
        HomeFragment.OnLocationChangeListener {

    @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var presenter: MainMvpPresenter<MainMvpView>

    private var lastKnownLocation: Location? = null

    companion object {
        val LOG_TAG = MainActivity::class.java.simpleName
        val REQUEST_ACCESS_MY_LOCATION_PERMISSIONS = 631992
        val HOME_FRAGMENT_TAG = "HOME_FRAGMENT_TAG"
        val GIFT_FRAGMENT_TAG = "GIFT_FRAGMENT_TAG"
        val ACCOUNT_FRAGMENT_TAG = "ACCOUNT_FRAGMENT_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.onAttach(this)

        navigation.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState == null) presenter.onHomeMenuClicked()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {

        if (requestCode == REQUEST_ACCESS_MY_LOCATION_PERMISSIONS) {
            val locationPermsResult = permissions.size == 1 && permissions[0] ==
                    Manifest.permission.ACCESS_FINE_LOCATION && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED

            val homeFragment = getFragmentByTag(HOME_FRAGMENT_TAG) as HomeFragment
            if (locationPermsResult) {
                homeFragment.onLocationPermissionGranted()
            } else {
                homeFragment.onLocationPermissionDenied()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> presenter.onHomeMenuClicked()
            R.id.navigation_gifts -> presenter.onGiftMenuClicked()
            R.id.navigation_account -> presenter.onAccountMenuClicked()
        }
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
            fragmentDispatchingAndroidInjector

    override fun showHomeScreen() {
        val homeFragment = HomeFragment.newInstance(lastKnownLocation)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, homeFragment, HOME_FRAGMENT_TAG)
                .commit()
    }

    override fun showGiftsScreen() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, GiftFragment(), GIFT_FRAGMENT_TAG)
                .commit()
    }

    override fun showAccountScreen() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, AccountFragment(), ACCOUNT_FRAGMENT_TAG)
                .commit()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getLocationRequest() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            val homeFragment = getFragmentByTag(HOME_FRAGMENT_TAG) as HomeFragment
            homeFragment.onLocationPermissionGranted()
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_ACCESS_MY_LOCATION_PERMISSIONS)
        }
    }

    override fun saveLastKnownLocation(location: Location) {
        lastKnownLocation = location
    }

    private fun getFragmentByTag(tag: String): Fragment {
        return supportFragmentManager.findFragmentByTag(tag)
    }
}
