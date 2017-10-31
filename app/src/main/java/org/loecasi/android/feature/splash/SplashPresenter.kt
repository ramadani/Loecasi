package org.loecasi.android.feature.splash

import org.loecasi.android.data.network.Auth
import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 10/31/17.
 */
class SplashPresenter<V : SplashMvpView> @Inject constructor(private val auth: Auth)
    : BasePresenter<V>(), SplashMvpPresenter<V> {

    override fun onAuthCheck() {
        if (auth.check()) getView()?.openMainScreen()
        else getView()?.openSignInScreen()
    }
}
