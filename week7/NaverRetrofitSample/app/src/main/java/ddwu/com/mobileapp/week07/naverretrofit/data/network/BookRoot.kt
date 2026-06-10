package ddwu.com.mobileapp.week07.naverretrofit.data.network

import com.google.gson.annotations.SerializedName


// BookRoot
data class Root(
    @SerializedName("items")
    val books: List<Book>,
)

// Book dto (item 저장)
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

