package org.loecasi.android.feature.gift.create

import dagger.Module
import dagger.Provides

/**
 * Created by dani on 11/1/17.
 */
@Module
class GiftCreateScreenModule {

    @Provides
    fun providePresenter(presenter: GiftCreatePresenter<GiftCreateMvpView>):
            GiftCreateMvpPresenter<GiftCreateMvpView> = presenter
}
