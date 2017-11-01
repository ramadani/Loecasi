package org.loecasi.android.feature.main.account

import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 11/1/17.
 */
class AccountPresenter<V : AccountMvpView> @Inject constructor() : BasePresenter<V>(),
        AccountMvpPresenter<V> {

}