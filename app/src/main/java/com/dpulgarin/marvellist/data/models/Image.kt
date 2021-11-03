package com.dpulgarin.marvellist.data.models

data class Image (
    val path: String = "",
    val extension: String = ""
) {
    fun getUrl() = "$path/landscape_incredible.$extension".convertToHttps()

    fun String.convertToHttps(): String {
        return this.replace("http", "https")
    }
}
