package org.loecasi.android.feature.base

import javax.inject.Inject

/**
 * Created by dani on 10/25/17.
 */
open class BasePresenter<V : MvpView> @Inject constructor() : MvpPresenter<V> {

    private var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        if (view != null) view = null
    }

    override fun getView(): V? = view
}
