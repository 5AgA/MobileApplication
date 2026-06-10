package ddwu.com.mobileapp.week04.activitytest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel(){
    private var name: MutableLiveData<String> = MutableLiveData()

    fun getName() = name

    fun updateName(newName: String) {
        name.value = newName
    }
}