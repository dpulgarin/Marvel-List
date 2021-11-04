package com.dpulgarin.marvellist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dpulgarin.marvellist.application.AppConstants
import com.dpulgarin.marvellist.data.models.CharacterEntity

@Database(entities = [CharacterEntity::class], version = AppConstants.DATABASE_VERSION)
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}