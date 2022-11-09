package com.example.ntttest.bookdetail.data.repository

import com.example.ntttest.bookdetail.data.dto.BookDetailDto
import com.example.ntttest.bookdetail.domain.model.Action
import com.example.ntttest.bookdetail.domain.model.Action.AddToWishlist
import com.example.ntttest.bookdetail.domain.model.Action.None
import com.example.ntttest.bookdetail.domain.model.BookDetail
import com.example.ntttest.bookdetail.domain.repository.BookDetailRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookDetailRepositoryImpl @Inject constructor() : BookDetailRepository {

    var contents = generateContents()

    override suspend fun getBookDetail(id: Int): BookDetail {
        return contents.find { it.id == id }?.let {
            BookDetail(
                id = it.id,
                title = it.title,
                description = it.description,
                author = it.author,
                imageText = it.imageText,
                createdDate = Date(it.createdDate),
                rating = it.rating,
                reviewCount = it.reviewCount,
                price = it.price,
                isInWishList = it.isInWishList
            )
        } ?: throw IndexOutOfBoundsException()
    }

    override suspend fun putInWishlist(id: Int) {}

    override suspend fun removeFromWishlist(id: Int) {}

    private fun generateContents(actionOnId: Int = -1, action: Action = None): List<BookDetailDto> {
        return (1..100).map { index ->
            BookDetailDto(
                id = index,
                title = "Title Book $index",
                description = "This is description, should be longer than book title still but keep it simple so reader can get the context of your book",
                author = "Dwiyasko Wicaksono",
                imageText = "Img $index",
                createdDate = when {
                    index % 2 == 0 -> {
                        // 2 days from now
                        Calendar.getInstance().apply {
                            add(Calendar.DATE, -1)
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
                price = (100000..500000).random().toDouble(),
                isInWishList = actionOnId == index && action == AddToWishlist
            )
        }
    }
}
