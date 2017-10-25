@file:JvmName("ExtensionsUtils")

package org.loecasi.android

import android.content.Context
import android.widget.Toast

/**
 * Created by dani on 10/25/17.
 */

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
