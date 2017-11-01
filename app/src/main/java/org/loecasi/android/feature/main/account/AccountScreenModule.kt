package org.loecasi.android.feature.main.account

import dagger.Module
import dagger.Provides

/**
 * Created by dani on 11/1/17.
 */
@Module
class AccountScreenModule {

    @Provides
    fun providePresenter(presenter: AccountPresenter<AccountMvpView>):
            AccountMvpPresenter<AccountMvpView> = presenter
}
