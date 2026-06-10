package ddum.com.mobile.week09.naverretrofitsample.data.network


data class BookRoot (
    val items : List<Book>
)

data class Book(
    val title: String,
    val image: String,
    val author: String,
    val publisher: String
) {
    override fun toString(): String {
        return "$title - $author"
    }
}


