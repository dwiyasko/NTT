package com.example.ntttest.bookdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ntttest.bookdetail.domain.model.BookDetail
import com.example.ntttest.bookdetail.domain.usecase.GetBookDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetail: GetBookDetail,
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
}
