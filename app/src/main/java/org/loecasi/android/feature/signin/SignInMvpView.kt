package org.loecasi.android.feature.signin

import org.loecasi.android.feature.base.MvpView

/**
 * Created by dani on 10/25/17.
 */
interface SignInMvpView : MvpView {

    fun signIn()

    fun openMainScreen()
}
