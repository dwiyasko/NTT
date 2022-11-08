package com.example.ntttest.home.domain.repository

import com.example.ntttest.home.domain.model.Book

interface BookRepository {
    suspend fun getBooks(offset: Int): List<Book>
}
