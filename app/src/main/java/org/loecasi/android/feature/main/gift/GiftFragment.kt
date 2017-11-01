package org.loecasi.android.feature.main.gift

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import org.loecasi.android.R
import javax.inject.Inject

/**
 * Created by dani on 10/30/17.
 */
class GiftFragment : Fragment(), GiftMvpView {

    @Inject lateinit var presenter: GiftMvpPresenter<GiftMvpView>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        presenter.onAttach(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_gift, container, false)
    }

    override fun onDetach() {
        presenter.onDetach()
        super.onDetach()
    }

    override fun openCreateScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openDetailScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
