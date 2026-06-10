package ddwu.com.mobileapp.week06.moviexmlparsing.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ddwu.com.mobileapp.week06.moviexmlparsing.data.MXPRepository
import ddwu.com.mobileapp.week06.naverxmlparsing.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MXPViewModel(private val mxpRepository: MXPRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>> = _movies

    fun showDailyBoxOffice(date: String) = viewModelScope.launch {
        var result : List<Movie>
        withContext(Dispatchers.IO) {
            result = mxpRepository.showDailyBoxOffice(date)
        }
        _movies.value = result
    }

}