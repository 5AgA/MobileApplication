package ddwu.com.mobileapp.week06.naverparsing.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ddwu.com.mobileapp.week06.naverparsing.data.NXPRepository
import ddwu.com.mobileapp.week06.naverparsing.data.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NXPViewModel(private val xpRepository: NXPRepository) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books : LiveData<List<Book>> = _books

    fun showBooksByKeyword(keyword: String) = viewModelScope.launch {
        var result : List<Book>
        withContext(Dispatchers.IO) {
            result = xpRepository.showBooksByKeyword(keyword)
        }
        _books.value = result
    }

}