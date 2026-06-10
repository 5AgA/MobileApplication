package ddwu.com.mobileapp.week03.roomexam01

import android.app.Application
import ddwu.com.mobile.roomexam01.data.FoodDatabase
import ddwu.com.mobileapp.week03.roomexam01.data.FoodRepository

class FoodApplication : Application() {
    val foodDatabase by lazy {
        FoodDatabase.getDatabase(this)
    }

    val foodRepo by lazy {
        FoodRepository(foodDatabase.foodDao())
    }

    val foodDao by lazy {
        foodDatabase.foodDao()
    }
}