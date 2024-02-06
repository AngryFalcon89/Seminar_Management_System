package com.example.seminarmanagementsystem.presentation.bookData.issueTheBook

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminarmanagementsystem.data.model.bookModel.IssueDTO
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
class IssueTheBookViewModel @Inject constructor(
private val bookUseCase: BookUseCase
) : ViewModel() {
    val issueResultValue = MutableStateFlow(IssueState())
    var _issueResultValue : StateFlow<IssueState> = issueResultValue

    fun issueTheBook(book: String) = viewModelScope.launch(Dispatchers.IO) {
        issueResultValue.value =
            _issueResultValue.value.copy(
                issueTo = IssueDTO(
                    name = issueResultValue.value.name,
                    email = issueResultValue.value.email,
                    remarks = issueResultValue.value.remarks,
                    returnDate = issueResultValue.value.returnDate
                )
            )
        bookUseCase.issueTheBook(bookId = book, issueTo = issueResultValue.value.issueTo).collect{
            when(it){
                is NetworkResult.Success -> {
                    Log.d(Constants.TAG,"issueTheBook: ${it.data?.body()?.message}")
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Error -> {

                }
            }
        }
    }

}