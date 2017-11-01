package org.loecasi.android.feature.main.account

import android.util.Log
import org.loecasi.android.data.network.Auth
import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 11/1/17.
 */
class AccountPresenter<V : AccountMvpView> @Inject constructor(private val auth: Auth) :
        BasePresenter<V>(), AccountMvpPresenter<V> {

    override fun onGetProfile() {
        getView()?.setProfile(auth.user())
    }
}
