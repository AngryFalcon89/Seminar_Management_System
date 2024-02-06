package com.example.seminarmanagementsystem.presentation.bookData.updateBook

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.BookUseCase
import com.example.seminarmanagementsystem.utils.Constants
import com.example.seminarmanagementsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateScreenViewModel @Inject constructor(
    private val bookUseCase: BookUseCase,

    ) : ViewModel() {

    val updateResultValue = MutableStateFlow(UpdateState())
    var _updateResultValue: StateFlow<UpdateState> = updateResultValue

    fun setSelectedBook(book: Book) {
        updateResultValue.value = UpdateState(
            accessionNumber = book.Accession_Number.toString(),
            author = book.Author,
            author1 = book.Author1,
            author2 = book.Author2,
            author3 = book.Author3,
            category1 = book.Category1,
            category2 = book.Category2,
            category3 = book.Category3,
            edition = book.Edition,
            id = book.ID.toString(),
            malAccNumber = book.MAL_ACC_Number.toString(),
            publisher = book.Publisher,
            publishingYear = book.Publishing_Year,
            title = book.Title,
            _id = book._id
        )
    }

    fun updateBook() = viewModelScope.launch(Dispatchers.IO) {
        val addResultValue = _updateResultValue.value // Added this line to get the value once
        Log.d(Constants.TAG, "updateBook: ${updateResultValue.value.bookRequest}")

        updateResultValue.value =
            _updateResultValue.value.copy(
                bookRequest = AddBookRequestDTO(
                    Accession_Number = addResultValue.accessionNumber,
                    Author = addResultValue.author,
                    Author1 = addResultValue.author1,
                    Author2 = addResultValue.author2,
                    Author3 = addResultValue.author3,
                    Category1 = addResultValue.category1,
                    Category2 = addResultValue.category2,
                    Category3 = addResultValue.category3,
                    Edition = addResultValue.edition,
                    ID = addResultValue.id,
                    MAL_ACC_Number = addResultValue.malAccNumber,
                    Publisher = addResultValue.publisher,
                    Publishing_Year = addResultValue.publishingYear,
                    Title = addResultValue.title
                )
            )
        Log.d(Constants.TAG, "updateBook: ${updateResultValue.value.bookRequest}")
        bookUseCase.updateBook(
            bookId = updateResultValue.value._id,
            book = updateResultValue.value.bookRequest
        ).collect {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d(Constants.TAG, "updateBook: ${it.data?.body()?.message}")
                    Log.d(Constants.TAG, "updateBook: ${it.message}")
                    Log.d(Constants.TAG, "updateBook: ${it.data.toString()}")
                    Log.d(Constants.TAG, "updateBook: ${it.data?.body()?.message}")
                    updateResultValue.value = UpdateState(message = it.data?.body()?.message?:"Something went wrong")
                }
                is NetworkResult.Loading ->{
                    Log.d(Constants.TAG, "addBook: isLoading")
                    updateResultValue.value = UpdateState(isLoading = true)
                }
                is NetworkResult.Error ->{
                    Log.d(Constants.TAG, "addBook: ${it.message}")
                    updateResultValue.value = UpdateState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
}