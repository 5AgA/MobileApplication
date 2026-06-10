package ddwu.com.mobileapp.week05.archictectureref.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ddwu.com.mobileapp.week05.archictectureref.data.RefRepository
import ddwu.com.mobileapp.week05.archictectureref.data.database.RefEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RefViewModel (val refRepository: RefRepository) : ViewModel() {
    // Flow 를 사용하여 지속 관찰
    val allRefs : LiveData<List<RefEntity>> = refRepository.allRefs.asLiveData()

    // one-shot 결과를 확인하고자 할 때 사용
    private var _name = MutableLiveData<String>()  // 값을 변경할 수 있음
    val nameData : LiveData<String> = _name  // _name을 가리키는 변수

    // viewModelScope 는 Dispatcher.Main 이므로 긴시간이 걸리는 IO 작업은 Dispatchers.IO 에서 작업
    fun findName(id: Int) = viewModelScope.launch {
        var result : String

        // 입출력 전용 스레드로 잠시 옮김
        withContext(Dispatchers.IO) {
            result = refRepository.getNameById(id)
        }

        _name.value = result
    }

    fun addRef(ref: RefEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            refRepository.insertRef(ref)
        }
    }
}