package org.loecasi.android.feature.splash

import dagger.Module
import dagger.Provides

/**
 * Created by dani on 10/31/17.
 */
@Module
class SplashScreenModule {

    @Provides
    fun providePresenter(presenter: SplashPresenter<SplashMvpView>):
            SplashMvpPresenter<SplashMvpView> = presenter
}
