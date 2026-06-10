package ddwu.com.mobileapp.week07.retrofittest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ddwu.com.mobileapp.week07.retrofittest.data.RefRepository
import ddwu.com.mobileapp.week07.retrofittest.data.database.RefEntity
import ddwu.com.mobileapp.week07.retrofittest.data.network.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RefViewModel (val refRepository: RefRepository) : ViewModel() {
    private var _movies = MutableLiveData<List<Movie>?>()
    val movies = _movies

    fun getMovies(key: String, date: String) = viewModelScope.launch {
        var result: List<Movie>?
        result = refRepository.getMovies(key, date)

        _movies.value = result
    }

}