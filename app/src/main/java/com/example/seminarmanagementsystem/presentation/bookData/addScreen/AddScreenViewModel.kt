package com.example.seminarmanagementsystem.presentation.bookData.addScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.BookUseCase
import com.example.seminarmanagementsystem.utils.Constants.TAG
import com.example.seminarmanagementsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val bookUseCase: BookUseCase
): ViewModel() {
    val addResultValue = MutableStateFlow(AddState())
    var _addResultValue : StateFlow<AddState> = addResultValue

    fun addBook()=viewModelScope.launch(Dispatchers.IO){
        Log.d(TAG, "addBook1: ${addResultValue.value}")

        addResultValue.value =
            _addResultValue.value.copy(
                bookRequest = AddBookRequestDTO(
                    Accession_Number = addResultValue.value.accessionNumber,
                    Author = addResultValue.value.author,
                    Author1 = addResultValue.value.author1,
                    Author2 = addResultValue.value.author2,
                    Author3 = addResultValue.value.author3,
                    Category1 = addResultValue.value.category1,
                    Category2 = addResultValue.value.category2,
                    Category3 = addResultValue.value.category3,
                    Edition = addResultValue.value.edition,
                    ID = addResultValue.value.id,
                    MAL_ACC_Number = addResultValue.value.malAccNumber,
                    Publisher = addResultValue.value.publisher,
                    Publishing_Year = addResultValue.value.publishingYear,
                    Title = addResultValue.value.title
                )
            )
        Log.d(TAG, "addBook2: ${addResultValue.value}")

        bookUseCase.addBook(addBookRequestDTO = addResultValue.value.bookRequest).collect {
            when(it){
                is NetworkResult.Success ->{
                    addResultValue.value = _addResultValue.value.copy(
                        message = it.data?.body()?.message?:"Oops! \uD83D\uDE4A It seems something went amiss. Our systems detected either a non-unique ID or an invalid entry. Let's try that again, shall we? \uD83D\uDD04",
                        showDialog = true,
                        isLoading = false
                    )

                }
                is NetworkResult.Loading ->{
                    Log.d(TAG, "addBook: isLoading")
                    addResultValue.value = _addResultValue.value.copy(isLoading = true)
                }
                is NetworkResult.Error ->{
                    Log.d(TAG, "addBook: ${it.message}")
                    addResultValue.value = _addResultValue.value.copy(
                        error = it.message?:"An Unexpected Error",
                        showDialog = true,
                        isLoading = false
                    )
                }
            }
        }
    }
}