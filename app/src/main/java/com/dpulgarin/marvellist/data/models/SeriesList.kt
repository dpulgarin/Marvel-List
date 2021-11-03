package com.dpulgarin.marvellist.data.models

import android.os.Parcel
import android.os.Parcelable

data class SeriesList (
    val available: Int = -1,
    val returned: Int = -1,
    val collectionURI: String? = "",
    val items: List<SeriesSummary> = listOf(),
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        arrayListOf<SeriesSummary>().apply {
            parcel.readList(this, SeriesSummary::class.java.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(available)
        parcel.writeInt(returned)
        parcel.writeString(collectionURI)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeriesList> {
        override fun createFromParcel(parcel: Parcel): SeriesList {
            return SeriesList(parcel)
        }

        override fun newArray(size: Int): Array<SeriesList?> {
            return arrayOfNulls(size)
        }
    }
}