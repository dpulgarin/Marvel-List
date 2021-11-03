package com.dpulgarin.marvellist.data.models

import com.dpulgarin.marvellist.core.extensions.convertToHttps

data class Image (
    val path: String = "",
    val extension: String = ""
) {
    fun getUrl() = "$path/landscape_incredible.$extension".convertToHttps()
}
