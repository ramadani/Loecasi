package org.loecasi.android.feature.signin

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.loecasi.android.data.model.User
import org.loecasi.android.feature.base.BasePresenter
import java.util.concurrent.Callable
import javax.inject.Inject

/**
 * Created by dani on 10/25/17.
 */
class SignInPresenter<V : SignInMvpView> @Inject constructor() : BasePresenter<V>(),
        SignInMvpPresenter<V> {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onAttach(view: V) {
        super.onAttach(view)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
    }

    override fun onCheckAuth() {
        if (auth.currentUser != null) {
            getView()?.openMainScreen()
        }
    }

    override fun onGoogleSignInClicked() {
        getView()?.signIn()
    }

    override fun authWithGoogle(account: GoogleSignInAccount) {
        registerUser(User(account.displayName!!, account.email!!))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("SignIn", it)
                }, {
                    Log.e("SignIn", it.localizedMessage, it)
                })

//        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
//        auth.signInWithCredential(credential).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                getView()?.openMainScreen()
//            } else {
//                getView()?.authFailed()
//            }
//        }
    }

    private fun registerUser(user: User): Observable<String> {
        return Observable.create { subscribe ->
            db.collection("users").add(user)
                    .addOnSuccessListener { subscribe.onNext(it.id) }
                    .addOnFailureListener { subscribe.onError(it) }
        }
    }
}
