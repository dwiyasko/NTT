package com.example.ntttest.bookdetail.data.dto

import java.util.*

data class BookDetailDto(
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val imageText: String,
    val createdDate: Long,
    val rating: Float,
    val reviewCount: Int,
    val price: Double,
    val isInWishList: Boolean,
)