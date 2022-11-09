package com.example.ntttest.bookdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ntttest.bookdetail.domain.model.BookDetail
import com.example.ntttest.bookdetail.domain.usecase.AddBookToWishlist
import com.example.ntttest.bookdetail.domain.usecase.GetBookDetail
import com.example.ntttest.bookdetail.domain.usecase.RemoveBookFromWishList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetail: GetBookDetail,
    private val addBookToWishlist: AddBookToWishlist,
    private val removeBookFromWishList: RemoveBookFromWishList,
) : ViewModel() {

    private val _bookDetail = MutableLiveData<BookDetail>()
    val bookDetail: LiveData<BookDetail> = _bookDetail

    fun loadBookDetail(id: Int) {
        getBookDetail.execute(
            id = id,
            onScope = viewModelScope,
            onSuccess = { _bookDetail.value = it }
        )
    }

    fun toggleWishlist() {
        if (bookDetail.value?.isInWishList == true) {
            removeBookFromWishList.execute(
                id = bookDetail.value?.id ?: -1,
                onScope = viewModelScope,
                onSuccess = { loadBookDetail(bookDetail.value?.id ?: -1) }
            )
        } else {
            addBookToWishlist.execute(
                id = bookDetail.value?.id ?: -1,
                onScope = viewModelScope,
                onSuccess = { loadBookDetail(bookDetail.value?.id ?: -1) }
            )
        }
    }
}
