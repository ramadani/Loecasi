package org.loecasi.android.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.loecasi.android.feature.main.MainActivity
import org.loecasi.android.feature.main.MainScreenModule
import org.loecasi.android.feature.signin.SignInActivity
import org.loecasi.android.feature.signin.SignInScreenModule

/**
 * Created by dani on 10/25/17.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(SignInScreenModule::class))
    abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector(modules = arrayOf(MainScreenModule::class))
    abstract fun bindMainActivity(): MainActivity
}
