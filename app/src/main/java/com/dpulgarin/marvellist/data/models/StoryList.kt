package com.dpulgarin.marvellist.data.models

import android.os.Parcel
import android.os.Parcelable

data class StoryList (
    val available: Int = -1,
    val returned: Int = -1,
    val collectionURI: String? = "",
    val items: List<StorySummary> = listOf(),
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        arrayListOf<StorySummary>().apply {
            parcel.readList(this, StorySummary::class.java.classLoader)
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

    companion object CREATOR : Parcelable.Creator<StoryList> {
        override fun createFromParcel(parcel: Parcel): StoryList {
            return StoryList(parcel)
        }

        override fun newArray(size: Int): Array<StoryList?> {
            return arrayOfNulls(size)
        }
    }

}