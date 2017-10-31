package org.loecasi.android.data.network

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import org.loecasi.android.data.model.User
import javax.inject.Inject

/**
 * Created by dani on 10/31/17.
 */
class AuthService @Inject constructor(
        private val database: FirebaseFirestore,
        private val auth: FirebaseAuth
) : Auth {

    override fun check(): Boolean = auth.currentUser != null

    override fun googleSignIn(token: String): Observable<User> {
        return Observable.create { subscribe ->
            val credential = GoogleAuthProvider.getCredential(token, null)

            auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    subscribe.onNext(User(currentUser?.displayName!!, currentUser.email!!))
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