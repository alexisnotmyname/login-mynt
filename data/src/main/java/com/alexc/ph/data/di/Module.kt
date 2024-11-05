package com.alexc.ph.data.di

import com.alexc.ph.data.repository.FakeLoginRepository
import com.alexc.ph.data.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface Module {

    @Binds
    fun bindLoginRepository(
        fakeLoginRepository: FakeLoginRepository
    ): LoginRepository

}