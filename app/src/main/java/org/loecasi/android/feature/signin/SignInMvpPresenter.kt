package org.loecasi.android.feature.signin

import org.loecasi.android.feature.base.MvpPresenter

/**
 * Created by dani on 10/25/17.
 */
interface SignInMvpPresenter<V : SignInMvpView> : MvpPresenter<V> {

    fun onGoogleSignInClicked()
}
