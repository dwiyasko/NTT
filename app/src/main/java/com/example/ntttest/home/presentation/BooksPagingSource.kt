package com.example.ntttest.home.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ntttest.home.domain.model.Book
import com.example.ntttest.home.domain.model.SortType
import com.example.ntttest.home.domain.usecase.GetBooks

class BooksPagingSource(
    private val getBooks: GetBooks,
    private val sortType: SortType
) : PagingSource<Int, Book>() {
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val currentPage =
            params.key?.let { getBooks.execute(it, sortType) } ?: getBooks.execute(1, sortType)

        val nextKey = if (currentPage.isEmpty()) null
        else (params.key ?: 1) + 5

        return LoadResult.Page(
            data = currentPage,
            prevKey = null,
            nextKey = nextKey,
        )
    }
}
