package org.loecasi.android.data.model

import android.net.Uri

/**
 * Created by dani on 10/30/17.
 */
data class User(
        val uid: String? = null,
        val name: String,
        val email: String,
        val photoUrl: Uri? = null
)
