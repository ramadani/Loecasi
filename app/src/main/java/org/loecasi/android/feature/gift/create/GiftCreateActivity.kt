package org.loecasi.android.feature.gift.create

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import dagger.android.AndroidInjection
import org.loecasi.android.R
import javax.inject.Inject

class GiftCreateActivity : AppCompatActivity(), GiftCreateMvpView {

    @Inject lateinit var presenter: GiftCreateMvpPresenter<GiftCreateMvpView>

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_create)
        ButterKnife.bind(this)
        presenter.onAttach(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
