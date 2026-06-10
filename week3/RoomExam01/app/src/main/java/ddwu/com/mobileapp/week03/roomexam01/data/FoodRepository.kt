package ddwu.com.mobileapp.week03.roomexam01.data

import ddwu.com.mobile.roomexam01.data.Food
import ddwu.com.mobile.roomexam01.data.FoodDao
import kotlinx.coroutines.flow.Flow

// private : 나만 통해서 DAO 를 사용할 수 있다 SSOT
class FoodRepository (private val foodDao: FoodDao) {
    val allFoods: Flow<List<Food>> = foodDao.getAllFoods()

    // UI 하고 Data 가 분리 됨
    suspend fun addFood(food: Food) {
        foodDao.insertFood(food)
    }

    suspend fun updateFood(food: Food) {
        foodDao.updateFood(food)
    }

    suspend fun deleteFood(food: Food) {
        foodDao.deleteFood(food)
    }

    suspend fun getFoodsByCountry(country : String) : List<Food> {
        val foodByCountry: List<Food> = foodDao.getFoodsByCountry(country)
        return foodByCountry
    }

    suspend fun getIdByFood(food: String) : Int {
        val id : Int = foodDao.getIdByFood(food)
        return id
    }
}