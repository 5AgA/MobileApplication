package ddum.com.mobile.week09.naverretrofitsample.ui

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ddum.com.mobile.week09.naverretrofitsample.data.NVRepository
import ddum.com.mobile.week09.naverretrofitsample.data.network.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NVViewModel(val nvRepository: NVRepository) : ViewModel() {

    // 책 목록 표시
    private val _books = MutableLiveData<List<Book>>()
    val books : LiveData<List<Book>> = _books

    fun getBooks(query: String) = viewModelScope.launch {
        _books.value = nvRepository.getBooks(query)
    }


    // 책 표지 표시
    private val _drawable = MutableLiveData<Bitmap>()
    val drawable : LiveData<Bitmap> = _drawable

    fun setImage(url: String?) = viewModelScope.launch{
        var bitmap : Bitmap
        withContext(Dispatchers.IO) {
            bitmap = nvRepository.getImage(url)
        }
        _drawable.value = bitmap
    }


}