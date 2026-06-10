package ddwu.com.mobileapp.week06.naverparsing.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ddwu.com.mobileapp.week06.naverparsing.data.NXPRepository

class NXPViewModelFactory(private val netRepository: NXPRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NXPViewModel::class.java)) {
            return NXPViewModel(netRepository) as T
        }
        return IllegalArgumentException("Unknown ViewModel class") as T
    }
}