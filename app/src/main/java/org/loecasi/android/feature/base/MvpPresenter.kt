package org.loecasi.android.feature.base

/**
 * Created by dani on 10/25/17.
 */
interface MvpPresenter<V : MvpView> {

    fun onAttach(view: V)

    fun onDetach()

    fun getView(): V?
}
