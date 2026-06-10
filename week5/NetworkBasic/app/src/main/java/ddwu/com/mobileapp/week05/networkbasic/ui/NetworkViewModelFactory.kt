package ddwu.com.mobileapp.week05.networkbasic.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ddwu.com.mobileapp.week05.networkbasic.data.NetworkRepository

class NetworkViewModelFactory (private val networkRepo: NetworkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NetworkViewModel::class.java)) {
            return NetworkViewModel(networkRepo) as T
        }
        return IllegalArgumentException("Unknown ViewModel class") as T
    }
}