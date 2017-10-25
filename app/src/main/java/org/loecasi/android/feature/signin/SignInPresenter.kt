package org.loecasi.android.feature.signin

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 10/25/17.
 */
class SignInPresenter<V : SignInMvpView> @Inject constructor() : BasePresenter<V>(),
        SignInMvpPresenter<V> {

    private lateinit var auth: FirebaseAuth

    override fun onAttach(view: V) {
        super.onAttach(view)
        auth = FirebaseAuth.getInstance()
    }

    override fun onGoogleSignInClicked() {
        getView()?.signIn()
    }

    override fun authWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                getView()?.openMainScreen()
            } else {
                getView()?.authFailed()
            }
        }
    }
}
