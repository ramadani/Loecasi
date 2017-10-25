package org.loecasi.android.feature.signin

import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 10/25/17.
 */
class SignInPresenter<V : SignInMvpView> @Inject constructor() : BasePresenter<V>(),
        SignInMvpPresenter<V> {

    override fun onGoogleSignInClicked() {
        getView()?.signIn()
    }
}
