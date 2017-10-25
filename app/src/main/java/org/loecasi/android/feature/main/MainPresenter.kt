package org.loecasi.android.feature.main

import org.loecasi.android.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by dani on 10/25/17.
 */
class MainPresenter<V : MainMvpView> @Inject constructor() : BasePresenter<V>(), MainMvpPresenter<V>
