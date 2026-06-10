package ddwu.com.mobileapp.week12.maptest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ddwu.com.mobileapp.week12.maptest.data.MapRepository
import ddwu.com.mobileapp.week12.maptest.data.database.RefEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapViewModel (val mapRepository: MapRepository) : ViewModel() {
    // Flow 를 사용하여 지속 관찰
    val allRefs : LiveData<List<RefEntity>> = mapRepository.allRefs.asLiveData()

    // one-shot 결과를 확인하고자 할 때 사용
    private var _name = MutableLiveData<String>()
    val nameData = _name

    // viewModelScope 는 Dispatcher.Main 이므로 긴시간이 걸리는 IO 작업은 Dispatchers.IO 에서 작업
    fun findName(id: Int) = viewModelScope.launch {
        var result : String
        withContext(Dispatchers.IO) {
            result = mapRepository.getNameById(id)
        }
        _name.value = result
    }

}