package org.loecasi.android.feature.signin

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import org.loecasi.android.feature.base.MvpPresenter

/**
 * Created by dani on 10/25/17.
 */
interface SignInMvpPresenter<V : SignInMvpView> : MvpPresenter<V> {

    fun onGoogleSignInClicked()

    fun authWithGoogle(account: GoogleSignInAccount)
}
