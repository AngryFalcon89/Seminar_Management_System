package com.example.seminarmanagementsystem.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.seminarmanagementsystem.data.apiService.ApiService
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.utils.Constants.limit

class BooksPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Book>() {
    private var totalBookCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val page = params.key ?: 1
        return try {
            val bookResponse = apiService.getBookList(
                limit = limit,
                page = page
            )
            totalBookCount += bookResponse.currentPage
            val books =
                bookResponse.books.distinctBy { it.ID } //news with same titles are removed

            LoadResult.Page(
                data = books,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (page < bookResponse.totalPages) page + 1 else null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}