package com.example.ntttest.home.data.dto

//This is data layer object, this kind of data is isolated, independent.
//Usually representation of external data source like API or DB
data class Book(
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val imageText: String,
    val createdDate: Long,
    val rating: Float,
    val reviewCount: Int,
    val price: Double,
)
