package com.dpulgarin.marvellist.data.models

import android.os.Parcel
import android.os.Parcelable

data class ComicList (
    val available: Int = -1,
    val returned: Int = -1,
    val collectionURI: String? = "",
    val items: List<ComicSummary> = listOf(),
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        arrayListOf<ComicSummary>().apply {
            parcel.readList(this, ComicSummary::class.java.classLoader)
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

    companion object CREATOR : Parcelable.Creator<ComicList> {
        override fun createFromParcel(parcel: Parcel): ComicList {
            return ComicList(parcel)
        }

        override fun newArray(size: Int): Array<ComicList?> {
            return arrayOfNulls(size)
        }
    }

    fun getComicsNames(): List<String> {
        val mutableList: MutableList<String> = mutableListOf()

        items.forEach { comicSummary ->
            mutableList.add(comicSummary.name)
        }

        return mutableList.toList()
    }

}
