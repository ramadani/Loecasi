package org.loecasi.android.feature.main

import org.loecasi.android.feature.base.MvpView

/**
 * Created by dani on 10/30/17.
 */
interface MainMvpView : MvpView {

    fun showHomeScreen()

    fun showGiftsScreen()

    fun showAccountScreen()
}
