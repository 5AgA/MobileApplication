package ddwu.com.mobileapp.week02.roomexam01.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDao {
    @Insert
    fun insertFood(vararg food: Food)
    @Update
    fun updateFood(food: Food)
    @Delete
    fun deleteFood(food: Food)
    @Query("SELECT * FROM food_table")
    fun getAllFoods(): List<Food>
    @Query("SELECT * FROM food_table WHERE country = :country")
    fun getFoodByCountry(country: String): List<Food>


}