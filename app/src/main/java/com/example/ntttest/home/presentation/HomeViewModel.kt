package com.example.ntttest.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ntttest.home.domain.usecase.GetBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooks: GetBooks,
) : ViewModel() {

    fun loadBooks() = Pager(PagingConfig(pageSize = 5)) {
        BooksPagingSource(getBooks)
    }.flow.cachedIn(viewModelScope)
}
