package com.dpulgarin.marvellist.data.models

import android.os.Parcel
import android.os.Parcelable

data class EventList (
    val available: Int = -1,
    val returned: Int = -1,
    val collectionURI: String? = "",
    val items: List<EventSummary> = listOf(),
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        arrayListOf<EventSummary>().apply {
            parcel.readList(this, EventSummary::class.java.classLoader)
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

    companion object CREATOR : Parcelable.Creator<EventList> {
        override fun createFromParcel(parcel: Parcel): EventList {
            return EventList(parcel)
        }

        override fun newArray(size: Int): Array<EventList?> {
            return arrayOfNulls(size)
        }
    }

    fun getEventsNames(): List<String> {
        val mutableList: MutableList<String> = mutableListOf()

        items.forEach { eventSummary ->
            mutableList.add(eventSummary.name)
        }

        return mutableList.toList()
    }
}