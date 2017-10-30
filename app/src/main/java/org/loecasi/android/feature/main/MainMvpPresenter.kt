package org.loecasi.android.feature.main

import org.loecasi.android.feature.base.MvpPresenter

/**
 * Created by dani on 10/30/17.
 */
interface MainMvpPresenter<V : MainMvpView> : MvpPresenter<V> {

    fun onHomeMenuClicked()

    fun onGiftMenuClicked()

    fun onAccountMenuClicked()
}