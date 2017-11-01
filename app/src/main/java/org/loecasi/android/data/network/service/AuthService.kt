package org.loecasi.android.data.network.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.reactivex.Observable
import org.loecasi.android.data.model.User
import org.loecasi.android.data.network.Auth
import javax.inject.Inject

/**
 * Created by dani on 10/31/17.
 */
class AuthService @Inject constructor(private val auth: FirebaseAuth) : Auth {

    override fun user(): User {
        val currentUser = auth.currentUser!!
        return User(name = currentUser.displayName!!, email = currentUser.email!!)
    }

    override fun check(): Boolean = auth.currentUser != null

    override fun googleSignIn(token: String): Observable<User> {
        return Observable.create { subscribe ->
            val credential = GoogleAuthProvider.getCredential(token, null)

            auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser!!
                    val user = User(name = currentUser.displayName!!, email = currentUser.email!!)
                    subscribe.onNext(user)
                } else {
                    subscribe.onError(it.exception!!)
                }
            }
        }
    }

    override fun logout() {
        auth.signOut()
    }
}