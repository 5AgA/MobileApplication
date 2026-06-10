package ddum.com.mobile.week09.naverretrofitsample.data

import android.graphics.Bitmap
import ddum.com.mobile.week09.naverretrofitsample.data.network.Book
import ddum.com.mobile.week09.naverretrofitsample.data.network.NVService

class NVRepository(private val nvService: NVService) {

    suspend fun getBooks(query: String) : List<Book>? {
        return nvService.getBooks(query)
    }

    suspend fun getImage(url: String?) : Bitmap {
        return nvService.getImage(url)
//        return null
    }
}