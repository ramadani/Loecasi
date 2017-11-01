package org.loecasi.android.feature.main.account

import org.loecasi.android.feature.base.MvpPresenter

/**
 * Created by dani on 11/1/17.
 */
interface AccountMvpPresenter<V : AccountMvpView> : MvpPresenter<V> {

    fun onGetProfile()
}
