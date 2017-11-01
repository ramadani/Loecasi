package org.loecasi.android.feature.main.gift

import org.loecasi.android.feature.base.MvpPresenter

/**
 * Created by dani on 11/1/17.
 */
interface GiftMvpPresenter<V :GiftMvpView> : MvpPresenter<V> {

    fun onFetch()

    fun onAddButtonClicked()

    fun onItemClicked()
}