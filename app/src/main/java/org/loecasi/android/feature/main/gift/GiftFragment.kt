package org.loecasi.android.feature.main.gift

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import dagger.android.support.AndroidSupportInjection
import org.loecasi.android.R
import org.loecasi.android.feature.gift.create.GiftCreateActivity
import javax.inject.Inject

/**
 * Created by dani on 10/30/17.
 */
class GiftFragment : Fragment(), GiftMvpView {

    @Inject lateinit var presenter: GiftMvpPresenter<GiftMvpView>

    @BindView(R.id.fab_add) lateinit var fabAdd: FloatingActionButton

    companion object {
        val LOG_TAG = GiftFragment::class.java.simpleName
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        presenter.onAttach(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_gift, container, false)
        ButterKnife.bind(this, view!!)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAdd.setOnClickListener { presenter.onAddButtonClicked() }
    }

    override fun onDetach() {
        presenter.onDetach()
        super.onDetach()
    }

    override fun openCreateScreen() {
        startActivity(Intent(activity, GiftCreateActivity::class.java))
    }

    override fun openDetailScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
