package ddwu.com.mobileapp.week06.moviexmlparsing.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ddwu.com.mobileapp.week06.moviexmlparsing.data.MXPRepository

class MXPViewModelFactory(private val netRepository: MXPRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MXPViewModel::class.java)) {
            return MXPViewModel(netRepository) as T
        }
        return IllegalArgumentException("Unknown ViewModel class") as T
    }
}