package ddwu.com.mobileapp.week04.roomexam01.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ddwu.com.mobileapp.week04.roomexam01.data.Food

import ddwu.com.mobileapp.week04.roomexam01.data.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FoodViewModel (val foodRepo: FoodRepository) : ViewModel() {
    var allFoods : LiveData<List<Food>> = foodRepo.allFoods.asLiveData()

    fun findFoodByCountry(country: String) : Deferred<List<Food>> {
        val deferredFoods : Deferred<List<Food>> = viewModelScope.async {
            foodRepo.getFoodByCountry(country)
        }
        return deferredFoods
    }

    fun addFood(food: Food) = viewModelScope.launch {
        foodRepo.addFood(food)
    }

    fun modifyFood(food: Food) = viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            foodRepo.modifyFood(food)
        }
    }

    fun removeFood(food: Food) = viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            foodRepo.removeFood(food)
        }
    }

    fun modifyFoodCountryByFood(food: Food) = viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            foodRepo.modifyFoodCountryByFood(food)
        }
    }

    fun removeFoodByName(food: Food) = viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            foodRepo.removeFoodByName(food)
        }
    }
}