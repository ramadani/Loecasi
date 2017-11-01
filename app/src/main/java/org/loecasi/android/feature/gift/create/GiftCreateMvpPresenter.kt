package org.loecasi.android.feature.gift.create

import org.loecasi.android.data.model.Location
import org.loecasi.android.feature.base.MvpPresenter

/**
 * Created by dani on 11/1/17.
 */
interface GiftCreateMvpPresenter<V : GiftCreateMvpView> : MvpPresenter<V> {

    fun onSaveClicked(name: String, description: String, location: Location)
}
