package org.loecasi.android.feature.signin

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import org.loecasi.android.R

/**
 * Created by dani on 10/25/17.
 */
@Module
class SignInScreenModule {

    @Provides
    fun provideGoogleSignInOptions(context: SignInActivity): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.google_web_client_id))
                .requestEmail()
                .build()
    }

    @Provides
    fun providePresenter(presenter: SignInPresenter<SignInMvpView>):
            SignInMvpPresenter<SignInMvpView> = presenter
}
