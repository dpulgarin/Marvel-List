package com.dpulgarin.marvellist.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class Character (
    val id: Int = -1,
    val name: String? = "",
    val description: String? = "",
    val modified: Date? = Date(),
    val resourceURI: String? = "",
    val urls: List<Url>? = listOf(),
    val thumbnail: Image? = Image(),
    val comics: ComicList? = ComicList(),
    val stories: StoryList? = StoryList(),
    val events: EventList? = EventList(),
    val series: SeriesList? = SeriesList(),
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDate(),
        parcel.readString(),
        arrayListOf<Url>().apply {
            parcel.readList(this, Url::class.java.classLoader)
        },
        parcel.readParcelable(Image::class.java.classLoader),
        parcel.readParcelable(ComicList::class.java.classLoader),
        parcel.readParcelable(StoryList::class.java.classLoader),
        parcel.readParcelable(EventList::class.java.classLoader),
        parcel.readParcelable(SeriesList::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeDate(modified)
        parcel.writeString(resourceURI)
        parcel.writeList(urls)
        parcel.writeParcelable(thumbnail, flags)
        parcel.writeParcelable(comics, flags)
        parcel.writeParcelable(stories, flags)
        parcel.writeParcelable(events, flags)
        parcel.writeParcelable(series, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }

        fun Parcel.writeDate(date: Date?) {
            writeLong(date?.time ?: -1)
        }

        fun Parcel.readDate(): Date? {
            val long = readLong()
            return if (long != -1L) Date(long) else null
        }
    }
}

// Room
@Entity
data class CharacterEntity (
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name="name")
    val name: String? = "",
    @ColumnInfo(name="description")
    val description: String? = "",
    @ColumnInfo(name="resource_uri")
    val resourceURI: String? = "",
    @ColumnInfo(name="thumbnail_path")
    val thumbnail_path: String? = "",
    @ColumnInfo(name="thumbnail_extension")
    val thumbnail_extension: String? = ""
)

fun List<CharacterEntity>.toCharacterList(): List<Character> {
    val resultList = mutableListOf<Character>()

    this.forEach{ characterEntity ->
        resultList.add(characterEntity.toCharacter())
    }

    return resultList
}

fun CharacterEntity.toCharacter(): Character = Character(
    this.id,
    this.name,
    this.description,
    null,
    this.resourceURI,
    null,
    Image(this.thumbnail_path, this.thumbnail_extension),
    null,
    null,
    null,
    null
)

fun Character.toCharacterEntity(): CharacterEntity = CharacterEntity(
    this.id,
    this.name,
    this.description,
    this.resourceURI,
    this.thumbnail?.path,
    this.thumbnail?.extension
)