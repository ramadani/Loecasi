package org.loecasi.android.data.model

import java.util.*

/**
 * Created by dani on 11/1/17.
 */
data class Gift(
        val title: String,
        val description: String,
        val location: Location,
        val uid: String,
        val createdAt: Date,
        val updatedAt: Date,
        val deletedAt: Date? = null
)
