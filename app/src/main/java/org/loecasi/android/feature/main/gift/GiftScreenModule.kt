package org.loecasi.android.feature.main.gift

import dagger.Module
import dagger.Provides

/**
 * Created by dani on 11/1/17.
 */
@Module
class GiftScreenModule {

    @Provides
    fun providePresenter(presenter: GiftPresenter<GiftMvpView>):
            GiftMvpPresenter<GiftMvpView> = presenter
}
