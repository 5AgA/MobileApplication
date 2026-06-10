package ddwu.com.mobileapp.week04.roomexam01.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ddwu.com.mobileapp.week04.roomexam01.data.FoodRepository

class FoodViewModelFactory(private val foodRepository: FoodRepository)
    : ViewModelProvider.Factory {

    // ViewModel 객체를 생성하는 함수를 재정의
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 생성하려는 클래스가 FoodViewModel 일 경우 객체 생성
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(foodRepository) as T
        }
        return IllegalArgumentException("Unknown ViewModel class") as T
    }
}