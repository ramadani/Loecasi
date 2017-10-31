package org.loecasi.android.feature.main.home

import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 10/31/17.
 */
class HomePresenter<V : HomeMvpView> @Inject constructor() : BasePresenter<V>(), HomeMvpPresenter<V>
