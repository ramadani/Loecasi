package org.loecasi.android.feature.gift.create

import org.loecasi.android.data.model.Location
import org.loecasi.android.data.network.Auth
import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 11/1/17.
 */
class GiftCreatePresenter<V : GiftCreateMvpView> @Inject constructor(private val auth: Auth) :
        BasePresenter<V>(), GiftCreateMvpPresenter<V> {

    override fun onSaveClicked(title: String, description: String, location: Location) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}