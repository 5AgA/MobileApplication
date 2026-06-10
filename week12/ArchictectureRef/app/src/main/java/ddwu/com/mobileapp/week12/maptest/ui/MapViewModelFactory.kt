package ddwu.com.mobileapp.week12.maptest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ddwu.com.mobileapp.week12.maptest.data.MapRepository

class MapViewModelFactory(private val mapRepository: MapRepository) : ViewModelProvider.Factory {
    // ViewModel 객체를 생성하는 함수를 재정의
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 생성하려는 클래스가 FoodViewModel 일 경우 객체 생성
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(mapRepository) as T
        }
        return IllegalArgumentException("Unknown ViewModel class") as T
    }

}