package org.loecasi.android.feature.main.gift

import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 11/1/17.
 */
class GiftPresenter<V : GiftMvpView> @Inject constructor() : BasePresenter<V>(),
        GiftMvpPresenter<V> {

    override fun onFetch() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAddButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
