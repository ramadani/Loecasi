package org.loecasi.android.feature.main.account

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.loecasi.android.R

/**
 * Created by dani on 10/30/17.
 */
class AccountFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_account, container, false)
    }
}