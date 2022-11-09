package com.example.ntttest.bookdetail.domain.usecase

import com.example.ntttest.bookdetail.domain.model.BookDetail
import com.example.ntttest.bookdetail.domain.repository.BookDetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookDetail @Inject constructor(
    private val repository: BookDetailRepository,
) {
    fun execute(id: Int, onScope: CoroutineScope, onSuccess: (BookDetail) -> Unit) {
        onScope.launch(Dispatchers.Main) {
            val bookDetail = withContext(Dispatchers.IO) {
                repository.getBookDetail(id)
            }

            onSuccess(bookDetail)
        }
    }
}
