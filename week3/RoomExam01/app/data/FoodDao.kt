package ddwu.com.mobile.roomexam01.data

interface FoodDao {

    fun insertFood(food: Food)

    fun updateFood(food: Food)

    fun deleteFood(food: Food)

    fun getAllFoods() : List<Food>

    fun getFoodsByCountry(country: String) : List<Food>

}