package org.loecasi.android.feature.main.account

import org.loecasi.android.data.model.User
import org.loecasi.android.feature.base.MvpView

/**
 * Created by dani on 11/1/17.
 */
interface AccountMvpView : MvpView {

    fun setProfile(user: User)
}
