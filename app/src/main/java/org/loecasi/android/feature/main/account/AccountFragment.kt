package org.loecasi.android.feature.main.account

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
class AccountFragment : Fragment(), AccountMvpView {

    @Inject lateinit var presenter: AccountMvpPresenter<AccountMvpView>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        presenter.onAttach(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_account, container, false)
    }

    override fun onDetach() {
        presenter.onDetach()
        super.onDetach()
    }
}
