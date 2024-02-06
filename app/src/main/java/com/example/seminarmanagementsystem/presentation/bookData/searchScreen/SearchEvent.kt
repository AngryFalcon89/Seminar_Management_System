package com.example.seminarmanagementsystem.presentation.bookData.searchScreen

sealed class SearchEvent {
    data class UpdateSearchQuery(
        val searchQuery: String
    ): SearchEvent()

    object SearchNews : SearchEvent()
}