package org.loecasi.android.feature.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import org.loecasi.android.feature.main.MainActivity
import org.loecasi.android.feature.signin.SignInActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashMvpView {

    @Inject lateinit var presenter: SplashMvpPresenter<SplashMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)
        presenter.onAuthCheck()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun openSignInScreen() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}
