
import com.example.seminarmanagementsystem.data.model.bookModel.Book

data class IssueBookDTO(
    val currentPage: Int,
    val issuedBooks: List<Book>,
    val message: String,
    val status: String,
    val totalPages: Int
)