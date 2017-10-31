package org.loecasi.android.data.network

import io.reactivex.Observable
import org.loecasi.android.data.model.User

/**
 * Created by dani on 10/31/17.
 */
interface Auth {

    fun check(): Boolean

    fun googleSignIn(token: String): Observable<User>

    fun logout()
}
