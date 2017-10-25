package org.loecasi.android.feature.main

import dagger.Module
import dagger.Provides

/**
 * Created by dani on 10/25/17.
 */
@Module
class MainScreenModule {

    @Provides
    fun providePresenter(presenter: MainPresenter<MainMvpView>):
            MainMvpPresenter<MainMvpView> = presenter
}
