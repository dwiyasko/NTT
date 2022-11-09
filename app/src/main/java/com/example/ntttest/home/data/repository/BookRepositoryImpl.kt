package com.example.ntttest.home.data.repository

import com.example.ntttest.home.domain.model.Book
import com.example.ntttest.home.domain.model.SortType
import com.example.ntttest.home.domain.model.SortType.*
import com.example.ntttest.home.domain.repository.BookRepository
import java.util.*
import java.util.Calendar.DATE
import javax.inject.Inject
import javax.inject.Singleton
import com.example.ntttest.home.data.dto.Book as BookDto

@Singleton
class BookRepositoryImpl @Inject constructor() : BookRepository {

    var contents = emptyList<BookDto>()

    override suspend fun getBooks(offset: Int, sortType: SortType): List<Book> {
        if (contents.isEmpty()) contents = generateContent()

        return takeContent(offset, sortType).map {
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

    private fun takeContent(offset: Int, sortType: SortType): List<BookDto> {
        if (offset > 100) return emptyList()

        return when (sortType) {
            None -> contents
            Asc -> contents.sortedBy { it.price }
            Desc -> contents.sortedByDescending { it.price }
        }.subList(offset - 1, offset + 4)
    }

    private fun generateContent(): List<BookDto> {
        return (1..100).map { index ->
            BookDto(
                id = index,
                title = "Title Book $index",
                description = "This is description, should be longer than book title still but keep it simple so reader can get the context of your book",
                author = "Dwiyasko Wicaksono",
                imageText = "Img $index",
                createdDate = when {
                    index % 2 == 0 -> {
                        // 2 days from now
                        Calendar.getInstance().apply {
                            add(DATE, -1)
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
                price = (100000..500000).random().toDouble()
            )
        }
    }

    companion object {
        const val LIMIT_PER_PAGE = 5
    }
}