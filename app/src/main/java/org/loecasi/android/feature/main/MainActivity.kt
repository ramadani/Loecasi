package org.loecasi.android.feature.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import org.loecasi.android.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainMvpView,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var presenter: MainMvpPresenter<MainMvpView>

    companion object {
        val LOG_TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.onAttach(this)

        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> presenter.onHomeMenuClicked()
            R.id.navigation_gifts -> presenter.onGiftMenuClicked()
            R.id.navigation_account -> presenter.onAccountMenuClicked()
        }
        return true
    }

    override fun showHomeScreen() {
        Log.d(LOG_TAG, "home screen")
    }

    override fun showGiftsScreen() {
        Log.d(LOG_TAG, "gifts screen")
    }

    override fun showAccountScreen() {
        Log.d(LOG_TAG, "account screen")
    }
}
