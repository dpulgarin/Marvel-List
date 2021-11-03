package com.dpulgarin.marvellist.data.models

import android.os.Parcel
import android.os.Parcelable
import com.dpulgarin.marvellist.core.extensions.convertToHttps

data class Image (
    val path: String? = "",
    val extension: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    fun getUrl() = "$path/landscape_incredible.$extension".convertToHttps()
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(path)
        parcel.writeString(extension)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}
