package org.loecasi.android.feature.main.account

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import org.loecasi.android.R
import org.loecasi.android.data.model.User
import org.loecasi.android.feature.util.PicassoCircleTransformation
import javax.inject.Inject

/**
 * Created by dani on 10/30/17.
 */
class AccountFragment : Fragment(), AccountMvpView {

    @Inject lateinit var presenter: AccountMvpPresenter<AccountMvpView>

    @BindView(R.id.iv_user_photo) lateinit var ivPhoto: ImageView
    @BindView(R.id.tv_user_name) lateinit var tvName: TextView
    @BindView(R.id.tv_user_email) lateinit var tvEmail: TextView

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        presenter.onAttach(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_account, container, false)
        ButterKnife.bind(this, view!!)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onGetProfile()
    }

    override fun onDetach() {
        presenter.onDetach()
        super.onDetach()
    }

    override fun setProfile(user: User) {
        with(user) {
            Picasso.with(context)
                    .load(photoUrl)
                    .transform(PicassoCircleTransformation())
                    .resize(84, 84)
                    .centerCrop()
                    .into(ivPhoto)
            tvName.text = name
            tvEmail.text = email
        }
    }
}
