package org.loecasi.android.feature.main.home

import dagger.Module
import dagger.Provides

/**
 * Created by dani on 10/31/17.
 */
@Module
class HomeScreenModule {

    @Provides
    fun providePresenter(presenter: HomePresenter<HomeMvpView>):
            HomeMvpPresenter<HomeMvpView> = presenter
}
