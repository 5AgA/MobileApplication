package ddwu.com.mobileapp.week02.fooddbexam_room.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDao {
    // Food entity table 을 찾아서 insert 해라
    @Insert
    fun insertFood ( vararg food: Food )   // insertFood(food1) or insertFood(food1, ...)

    @Update
    fun updateFood( food: Food )

    @Delete
    fun deleteFood( food: Food )

    @Query("SELECT * FROM food_table")  // 임의로 질의 하고 싶을 때 사용
    fun getAllFoods(): List<Food>

    @Query("SELECT * FROM food_table WHERE country = (:country)")
    fun showFoodByCountry(country: String) : List<Food>

}