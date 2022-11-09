package com.example.ntttest.bookdetail.domain.repository

import com.example.ntttest.bookdetail.domain.model.BookDetail

interface BookDetailRepository {

    suspend fun getBookDetail(id: Int): BookDetail
    suspend fun putInWishlist(id: Int)
    suspend fun removeFromWishlist(id: Int)
}
