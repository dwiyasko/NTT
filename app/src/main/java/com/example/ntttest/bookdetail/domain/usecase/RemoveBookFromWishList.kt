package com.example.ntttest.bookdetail.domain.usecase

import com.example.ntttest.bookdetail.domain.repository.BookDetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoveBookFromWishList @Inject constructor(
    private val repository: BookDetailRepository,
) {
    fun execute(id: Int, onScope: CoroutineScope, onSuccess: () -> Unit) {
        onScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.removeFromWishlist(id)
            }

            onSuccess()
        }
    }
}
