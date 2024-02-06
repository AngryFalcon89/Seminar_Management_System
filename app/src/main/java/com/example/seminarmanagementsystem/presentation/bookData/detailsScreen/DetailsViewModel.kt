package com.example.seminarmanagementsystem.presentation.bookData.detailsScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.BookUseCase
import com.example.seminarmanagementsystem.presentation.bookData.addScreen.AddState
import com.example.seminarmanagementsystem.utils.Constants.TAG
import com.example.seminarmanagementsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val bookUseCase: BookUseCase
) : ViewModel() {

    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "deleteBook: ${book._id}")
        bookUseCase.deleteBook(bookId = book._id).collect {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d(TAG, "deleteBook: ${it.data?.body()?.message}")
                    Log.d(TAG, "deleteBook: ${it.message}")
                    Log.d(TAG, "deleteBook: ${it.data.toString()}")
                    Log.d(TAG, "deleteBook: ${it.data?.body()?.message}")
                }

                is NetworkResult.Loading -> {
                }

                is NetworkResult.Error -> {
                }
            }
        }
    }

    fun returnBook(book:Book) = viewModelScope.launch(Dispatchers.IO) {
        bookUseCase.returnTheBook(bookId = book._id).collect{
            when(it){
                is NetworkResult.Success -> {
                    Log.d(TAG,"return Book: ${it.data?.body()?.message}")
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Error -> {

                }
            }
        }
    }

}