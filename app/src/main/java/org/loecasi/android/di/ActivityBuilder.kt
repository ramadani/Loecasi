package org.loecasi.android.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.loecasi.android.feature.main.MainActivity
import org.loecasi.android.feature.main.MainScreenModule
import org.loecasi.android.feature.main.MainFragmentProvider
import org.loecasi.android.feature.signin.SignInActivity
import org.loecasi.android.feature.signin.SignInScreenModule
import org.loecasi.android.feature.splash.SplashActivity
import org.loecasi.android.feature.splash.SplashScreenModule

/**
 * Created by dani on 10/25/17.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(SplashScreenModule::class))
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(SignInScreenModule::class))
    abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector(modules = arrayOf(
            MainScreenModule::class,
            MainFragmentProvider::class
    ))
    abstract fun bindMainActivity(): MainActivity
}
