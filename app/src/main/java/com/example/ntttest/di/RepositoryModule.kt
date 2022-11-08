package com.example.ntttest.di

import com.example.ntttest.home.data.repository.BookRepositoryImpl
import com.example.ntttest.home.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindBookRepository(impl: BookRepositoryImpl): BookRepository
}