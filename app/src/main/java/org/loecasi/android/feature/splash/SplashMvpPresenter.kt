package org.loecasi.android.feature.splash

import org.loecasi.android.feature.base.MvpPresenter

/**
 * Created by dani on 10/31/17.
 */
interface SplashMvpPresenter<V : SplashMvpView> : MvpPresenter<V> {

    fun onAuthCheck()
}
