package org.loecasi.android.data.network

import org.loecasi.android.data.model.Gift

/**
 * Created by dani on 11/1/17.
 */
interface GiftRepository {

    fun create(gift: Gift)

    fun update(gift: Gift, id: String)

    fun remove(id: String)
}
