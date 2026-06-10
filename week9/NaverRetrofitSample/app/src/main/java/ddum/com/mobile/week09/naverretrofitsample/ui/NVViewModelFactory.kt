package ddum.com.mobile.week09.naverretrofitsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ddum.com.mobile.week09.naverretrofitsample.data.NVRepository

class NVViewModelFactory(private val repo: NVRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 생성하려는 클래스가 NVViewModel 일 경우 객체 생성
        if (modelClass.isAssignableFrom(NVViewModel::class.java)) {
            return NVViewModel(repo) as T
        }
        return IllegalArgumentException("Unknown ViewModel class") as T
    }
}