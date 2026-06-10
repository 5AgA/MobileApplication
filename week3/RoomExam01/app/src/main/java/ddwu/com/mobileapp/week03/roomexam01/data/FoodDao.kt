package ddwu.com.mobile.roomexam01.data

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

    @Query("SELECT * FROM food_table")
    fun getAllFoods() : Flow<List<Food>>

    @Query("SELECT * FROM food_table WHERE country = (:country)")
    suspend fun getFoodsByCountry(country: String) : List<Food>

    @Query("SELECT _id FROM food_table WHERE food = (:food)")
    suspend fun getIdByFood(food: String) : Int
}