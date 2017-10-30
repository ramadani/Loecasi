package org.loecasi.android.feature.main

import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 10/30/17.
 */
class MainPresenter<V : MainMvpView> @Inject constructor() : BasePresenter<V>(),
        MainMvpPresenter<V> {

    override fun onHomeMenuClicked() {
        getView()?.showHomeScreen()
    }

    override fun onGiftMenuClicked() {
        getView()?.showGiftsScreen()
    }

    override fun onAccountMenuClicked() {
        getView()?.showAccountScreen()
    }
}
