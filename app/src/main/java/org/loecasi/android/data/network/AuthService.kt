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

    override fun isRegistered(email: String): Observable<Boolean> {
        return Observable.create { subscribe ->
            val user = database.collection("users").whereEqualTo("email", email)
            user.get().addOnCompleteListener {
                when {
                    it.isSuccessful -> when {
                        it.result.size() > 0 -> subscribe.onNext(true)
                        else -> subscribe.onNext(false)
                    }
                    else -> subscribe.onError(it.exception!!)
                }
            }
        }
    }

    override fun register(user: User): Observable<String> {
        return Observable.create { subscribe ->
            database.collection("users").add(user)
                    .addOnSuccessListener { subscribe.onNext(it.id) }
                    .addOnFailureListener { subscribe.onError(it) }
        }
    }

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