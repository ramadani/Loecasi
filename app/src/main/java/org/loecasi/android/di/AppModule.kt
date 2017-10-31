package org.loecasi.android.di

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import org.loecasi.android.data.network.Auth
import org.loecasi.android.data.network.AuthService
import javax.inject.Singleton

/**
 * Created by dani on 10/25/17.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app

    @Provides
    @Singleton
    fun provideAuth(): Auth {
        return AuthService(FirebaseAuth.getInstance())
    }
}
