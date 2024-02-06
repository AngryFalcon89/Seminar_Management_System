package com.example.seminarmanagementsystem.domain.useCases.bookUseCase


data class BookUseCase(
    val getBookListUseCase: GetBookListUseCase,
    val searchBook: SearchBook,
    val issueBook: IssueBook,
    val addBook: AddBook,
    val deleteBook: DeleteBook,
    val updateBook: UpdateBook,
    val issueTheBook: IssueTheBook,
    val returnTheBook: ReturnTheBook
)