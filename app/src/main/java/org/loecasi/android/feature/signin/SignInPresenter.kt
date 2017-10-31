package org.loecasi.android.feature.signin

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.loecasi.android.data.network.Auth
import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 10/25/17.
 */
class SignInPresenter<V : SignInMvpView> @Inject constructor(private val auth: Auth)
    : BasePresenter<V>(), SignInMvpPresenter<V> {

    override fun onGoogleSignInClicked() {
        getView()?.signIn()
    }

    override fun authWithGoogle(account: GoogleSignInAccount) {
        auth.googleSignIn(account.idToken!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getView()?.openMainScreen()
                }, {
                    getView()?.authFailed()
                })
    }
}
