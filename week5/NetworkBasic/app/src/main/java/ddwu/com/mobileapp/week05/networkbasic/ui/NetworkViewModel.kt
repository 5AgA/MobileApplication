package ddwu.com.mobileapp.week05.networkbasic.ui

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ddwu.com.mobileapp.week05.networkbasic.data.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetworkViewModel (val networkRepo: NetworkRepository) : ViewModel() {
    private val _textResult = MutableLiveData<String>()
    val textData : LiveData<String> = _textResult

    fun getConnInfo() {
        _textResult.value = networkRepo.getConnInfoText()
    }

    fun getOnlineInfo() {
        _textResult.value = networkRepo.getOnlineStatusText()
    }

    fun getNetworkText(address: String) = viewModelScope.launch {
        var result: String
        withContext(Dispatchers.IO) {
           result = networkRepo.getNetworkText(address)
        }
        _textResult.value = result
    }


    private val _bitmap = MutableLiveData<Bitmap?>()
    val bitmapData : LiveData<Bitmap?> = _bitmap

    fun getNetworkImage(address: String) = viewModelScope.launch {
        var result: Bitmap? = null
        withContext(Dispatchers.IO) {
            // 이미지 요청
            result = networkRepo.getNetworkImage(address)
        }
        _bitmap.value = result
    }


    // POST 요청 구현
    fun setNetworkText(address: String, data: String) = viewModelScope.launch {
        var result: String
        withContext(Dispatchers.IO) {
            result = networkRepo.getNetworkPost(address, data)
        }
        _textResult.value = result
    }

}