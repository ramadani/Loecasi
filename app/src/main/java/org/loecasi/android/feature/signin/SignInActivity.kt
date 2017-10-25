package org.loecasi.android.feature.signin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import dagger.android.AndroidInjection
import org.loecasi.android.R
import org.loecasi.android.feature.main.MainActivity
import org.loecasi.android.toast
import javax.inject.Inject

class SignInActivity : AppCompatActivity(), SignInMvpView {

    @Inject lateinit var presenter: SignInMvpPresenter<SignInMvpView>
    @Inject lateinit var googleSignInOptions: GoogleSignInOptions

    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var btnGoogleSignIn: Button

    companion object {
        val LOG_TAG = SignInActivity::class.java.simpleName
        val RC_SIGN_IN = 5392
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        presenter.onAttach(this)

        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, { toast("Connection Failed: ${it.errorMessage}") })
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build()

        btnGoogleSignIn = findViewById(R.id.btn_google_sign_in)
        btnGoogleSignIn.setOnClickListener { presenter.onGoogleSignInClicked() }
    }

    override fun onStart() {
        super.onStart()
        presenter.onCheckAuth()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            onReceiveGoogleSignInResult(data)
        }
    }

    override fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun openMainScreen() {
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
        finish()
    }

    override fun authFailed() {
        toast("Authentication failed")
    }

    private fun onReceiveGoogleSignInResult(data: Intent?) {
        val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
        if (result.isSuccess) {
            result.signInAccount?.let { presenter.authWithGoogle(it) }
        } else {
            toast("Google Sign In failed")
        }
    }
}
