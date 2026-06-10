package ddwu.com.mobileapp.week04.roomexam01.data

import kotlinx.coroutines.flow.Flow


class FoodRepository(private val foodDao: FoodDao) {
    val allFoods : Flow<List<Food>> = foodDao.getAllFoods()

    suspend fun getFoodByCountry(country: String) : List<Food> {
        val foods = foodDao.getFoodsByCountry(country)
        return foods
    }

    suspend fun addFood(food: Food) {
        foodDao.insertFood(food)
    }

    suspend fun modifyFood(food: Food) {
        foodDao.updateFood(food)
    }

    suspend fun removeFood(food: Food) {
        foodDao.deleteFood(food)
    }

    suspend fun modifyFoodCountryByFood(food: Food) {
        foodDao.updateFoodCountryByName(food.foodName!!, food.country!!)
    }

    suspend fun removeFoodByName(food: Food) {
        foodDao.deleteFoodByName(food.foodName!!)
    }
}