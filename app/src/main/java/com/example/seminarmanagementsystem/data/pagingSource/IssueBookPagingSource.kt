package com.example.seminarmanagementsystem.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.seminarmanagementsystem.data.apiService.AuthApiService
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.utils.Constants

class IssueBookPagingSource(
    private val authApiService: AuthApiService
) : PagingSource<Int, Book>() {

    private var totalBookCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val page = params.key ?: 1
        return try {
            val bookResponse = authApiService.issueBooks(limit = Constants.limit, page = page)
            totalBookCount += bookResponse.issuedBooks.size
            val books =
                bookResponse.issuedBooks.distinctBy { it.ID }

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