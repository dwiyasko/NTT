package com.example.ntttest.home.domain.repository

import com.example.ntttest.home.domain.model.Book
import com.example.ntttest.home.domain.model.SortType

interface BookRepository {
    suspend fun getBooks(offset: Int, sortType: SortType): List<Book>
}
