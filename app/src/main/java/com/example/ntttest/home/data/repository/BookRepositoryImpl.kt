package com.example.ntttest.home.data.repository

import com.example.ntttest.home.domain.model.Book
import com.example.ntttest.home.domain.repository.BookRepository
import java.util.*
import java.util.Calendar.DATE
import javax.inject.Inject
import com.example.ntttest.home.data.dto.Book as BookDto

class BookRepositoryImpl @Inject constructor() : BookRepository {
    override suspend fun getBooks(offset: Int): List<Book> {
        return generateContent(offset).map {
            Book(
                it.id,
                it.title,
                it.description,
                it.author,
                it.imageText,
                Date(it.createdDate),
                it.rating,
                it.reviewCount,
                it.price
            )
        }
    }

    private fun generateContent(offset: Int): List<BookDto> {
        if (offset > 100) return emptyList()

        return (offset..(offset + LIMIT_PER_PAGE)).map { index ->
            BookDto(
                id = index,
                title = "Title Book $index",
                description = "This is description, should be longer that book title but keep it simple so reader can get the context of your book",
                author = "Dwiyasko Wicaksono",
                imageText = "Img $index",
                createdDate = when {
                    index % 2 == 0 -> {
                        // 2 days from now
                        Calendar.getInstance().apply {
                            add(DATE, -2)
                        }.timeInMillis
                    }
                    else -> {
                        1641401154000 // 5 January 2022
                    }
                },
                rating = when {
                    index % 4 == 0 -> {
                        5.0f
                    }
                    else -> 3.0f
                },
                reviewCount = when {
                    index % 6 == 0 -> {
                        50
                    }
                    else -> 19
                },
                price = 250000.0
            )
        }
    }

    companion object {
        val LIMIT_PER_PAGE = 5
    }
}