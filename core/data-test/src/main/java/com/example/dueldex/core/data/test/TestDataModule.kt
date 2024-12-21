package com.example.dueldex.core.data.test

import com.example.dueldex.core.data.di.DataModule
import com.example.dueldex.core.data.repository.DecksRepository
import com.example.dueldex.core.data.repository.UserDataRepository
import com.example.dueldex.core.data.repository.YugiohRepository
import com.example.dueldex.core.data.test.repository.FakeYugiohRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class],
)
internal interface TestDataModule {
//    @Binds
//    fun bindsDecksRepository(
//
//    ): DecksRepository
//
//    @Binds
//    fun bindsUserDataRepository(
//
//    ): UserDataRepository

    @Binds
    fun bindsYugiohRepository(
        fakeYugiohRepository: FakeYugiohRepository
    ): YugiohRepository
}