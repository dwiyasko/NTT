package com.example.ntttest.bookdetail.domain.model

import java.util.*

data class BookDetail(
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val imageText: String,
    val createdDate: Date,
    val rating: Float,
    val reviewCount: Int,
    val price: Double,
    val isInWishList: Boolean,
)
