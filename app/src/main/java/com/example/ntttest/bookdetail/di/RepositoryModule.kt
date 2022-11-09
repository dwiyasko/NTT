package com.example.ntttest.bookdetail.di

import com.example.ntttest.bookdetail.data.repository.BookDetailRepositoryImpl
import com.example.ntttest.bookdetail.domain.repository.BookDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindBookDetailRepository(impl: BookDetailRepositoryImpl): BookDetailRepository
}
