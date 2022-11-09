package com.example.ntttest.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.ntttest.home.domain.model.Book
import com.example.ntttest.home.domain.model.SortType.*
import com.example.ntttest.home.domain.usecase.GetBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooks: GetBooks,
) : ViewModel() {

    private val _sortType = MutableLiveData(None)

    private lateinit var pagingSource: PagingSource<Int, Book>

    fun loadBooks() = Pager(PagingConfig(pageSize = 5)) {
        BooksPagingSource(getBooks, _sortType.value ?: Asc).apply {
            pagingSource = this
        }
    }.flow.cachedIn(viewModelScope)

    fun toggleSort() {
        if (_sortType.value == None || _sortType.value == Desc) {
            _sortType.value = Asc
        } else {
            _sortType.value = Desc
        }

        pagingSource.invalidate()
    }
}
