package ddwu.com.mobileapp.week02.fooddbexam_room

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import ddwu.com.mobileapp.week02.fooddbexam_room.data.Food
import ddwu.com.mobileapp.week02.fooddbexam_room.data.FoodDao
import ddwu.com.mobileapp.week02.fooddbexam_room.data.FoodDatabase
import ddwu.com.mobileapp.week02.fooddbexam_room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val foodDatabase by lazy {
        FoodDatabase.getDatabase(this)
    }

    val foodDao by lazy {
        foodDatabase.foodDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        Thread {
            showAllFoods()
        }.start()
    }

    fun showAllFoods() {
        val foods: List<Food> = foodDao.getAllFoods()

        for (food in foods) {
            Log.d(TAG, food.toString())
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnShowFood -> {
//                show food by country
                Thread {
                    foodDao.showFoodByCountry("대한민국")
                }.start()
            }
            R.id.btnAdd -> {
//                add food
                Thread {
                    val country : List<Food> = foodDao.insertFood(Food(0, "순두부찌개", "대한민국"))

                    for (food in country) {
                        Log.d(TAG, food.toString())
                    }
                }.start()
            }
            R.id.btnModify -> {
//                modify food
                Thread {
                    foodDao.updateFood(Food(0, "마라탕", "중국"))
                }.start()
            }
            R.id.btnRemove -> {
//                remove food
                Thread {
                    foodDao.deleteFood(Food(1, "순두부찌개", "대한민국"))
                }.start()
            }
        }
    }




}