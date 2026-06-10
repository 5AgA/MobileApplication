package ddwu.com.mobileapp.week07.naverretrofit.ui

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ddwu.com.mobileapp.week07.naverretrofit.data.NVRepository
import ddwu.com.mobileapp.week07.naverretrofit.data.network.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NVViewModel(val nvRepository: NVRepository) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books : LiveData<List<Book>> = _books

    fun getBooks(query: String, id: String, secret: String) = viewModelScope.launch {
        _books.value = nvRepository.getBooks(query, id, secret)     // INaverBookSearch에 coroutine 적용할 것
    }

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap : LiveData<Bitmap> = _bitmap

//    fun setImage(url: String?) = viewModelScope.launch{
//        var bitmap : Bitmap
//        withContext(Dispatchers.IO) {
//            bitmap = nvRepository.getImage(url)
//        }
//        _bitmap.value = bitmap
//    }


}