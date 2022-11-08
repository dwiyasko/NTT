package com.example.ntttest.home.domain.usecase

import com.example.ntttest.home.domain.model.Book
import com.example.ntttest.home.domain.repository.BookRepository
import javax.inject.Inject

class GetBooks @Inject constructor(
    private val repository: BookRepository,
) {
    suspend fun execute(offset: Int): List<Book> {
        return repository.getBooks(offset)
    }
}
