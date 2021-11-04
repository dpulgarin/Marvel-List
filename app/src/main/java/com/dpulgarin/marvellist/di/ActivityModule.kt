package com.dpulgarin.marvellist.di

import com.dpulgarin.marvellist.repository.CharacterRepository
import com.dpulgarin.marvellist.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {
    @Binds
    abstract fun dataSource(repoImpl: CharacterRepositoryImpl): CharacterRepository
}