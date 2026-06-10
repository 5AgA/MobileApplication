package ddwu.com.mobileapp.week07.naverretrofit.data

import android.graphics.Bitmap
import android.widget.ImageView
import ddwu.com.mobileapp.week07.naverretrofit.data.network.Book
import ddwu.com.mobileapp.week07.naverretrofit.data.network.NVService

class NVRepository(private val nvService: NVService) {

    suspend fun getBooks(query: String, id: String, secret: String) : List<Book>? {
        return nvService.getBooks(query, id, secret)
    }

//    suspend fun getImage(url: String?) : Bitmap {
//        return nvService.getImage(url)
//    }
}