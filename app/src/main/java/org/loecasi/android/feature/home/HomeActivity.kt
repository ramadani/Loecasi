package org.loecasi.android.feature.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.loecasi.android.R

class HomeActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView
            .OnNavigationItemSelectedListener {

        when (it.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
