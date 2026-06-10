package ddwu.com.mobileapp.week03.roomexam01

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import ddwu.com.mobile.roomexam01.data.Food
import ddwu.com.mobile.roomexam01.data.FoodDao
import ddwu.com.mobile.roomexam01.data.FoodDatabase
import ddwu.com.mobileapp.week03.roomexam01.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    // view binding object
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val foodRepo by lazy {
        (application as FoodApplication).foodRepo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // init RecyclerView
        val adapter = FoodAdapter(ArrayList<Food>())

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.foodRecyclerView.layoutManager = layoutManager
        binding.foodRecyclerView.adapter = adapter

        // get all foods
        val foodFlow : Flow<List<Food>> = foodRepo.allFoods

        CoroutineScope(Dispatchers.Main).launch {
            foodFlow.distinctUntilChanged().collect { foods ->
                for (food in foods) {
                    Log.d(TAG, food.toString())
                }
            }
        }

        // food by country
        binding.btnShow.setOnClickListener {
            val countryName = binding.etCountry.text.toString()

            CoroutineScope(Dispatchers.Main).launch {
                val foods = foodRepo.getFoodsByCountry(countryName)
                adapter.foods.clear()
                adapter.foods.addAll(foods)
                adapter.notifyDataSetChanged()
            }
        }


        // insert new food
        binding.btnInsert.setOnClickListener {
            val foodName = binding.etFood.text.toString()
            val countryName = binding.etCountry.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                foodRepo.addFood(Food(0, foodName, countryName))
            }
        }

        // update food
        binding.btnUpdate.setOnClickListener {
            val foodName = binding.etFood.text.toString()
            val countryName = binding.etCountry.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val id = foodRepo.getIdByFood(foodName)
                foodRepo.updateFood(Food(id, foodName, countryName))
            }

        }

        // update food
        binding.btnDelete.setOnClickListener {
            val foodName = binding.etFood.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val id = foodRepo.getIdByFood(foodName)
                foodRepo.deleteFood(Food(id, "", ""))
            }
        }

        adapter.setOnItemLongClickListener(object : FoodAdapter.OnItemLongClickListener {
            override fun onItemLongClickListener(view: View, pos: Int) {
                val food = adapter.foods[pos]

                CoroutineScope(Dispatchers.IO).launch {
                    foodRepo.deleteFood(food)
                }
            }
        })
    }
}