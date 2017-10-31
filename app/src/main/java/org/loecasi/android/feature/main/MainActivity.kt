package org.loecasi.android.feature.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
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
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var presenter: MainMvpPresenter<MainMvpView>

    private lateinit var fragmentManager: FragmentManager

    companion object {
        val LOG_TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.onAttach(this)

        fragmentManager = supportFragmentManager
        navigation.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState == null) presenter.onHomeMenuClicked()
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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
            fragmentDispatchingAndroidInjector

    override fun showHomeScreen() {
        fragmentManager.beginTransaction().replace(R.id.fl_main, HomeFragment()).commit()
    }

    override fun showGiftsScreen() {
        fragmentManager.beginTransaction().replace(R.id.fl_main, GiftFragment()).commit()
    }

    override fun showAccountScreen() {
        fragmentManager.beginTransaction().replace(R.id.fl_main, AccountFragment()).commit()
    }
}
