package ddwu.com.mobileapp.week04.roomexam01.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert
    suspend fun insertFood(food: Food)

    @Update
    suspend fun updateFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("UPDATE food_table SET country = :country WHERE food = :newName")
    fun updateFoodCountryByName(newName : String, country: String)

    @Query("DELETE FROM food_table WHERE food = :foodName")
    fun deleteFoodByName(foodName: String)

    @Query("SELECT * FROM food_table")
    fun getAllFoods() : Flow<List<Food>>

    @Query("SELECT * FROM food_table WHERE country = :country")
    suspend fun getFoodsByCountry(country: String) : List<Food>

}