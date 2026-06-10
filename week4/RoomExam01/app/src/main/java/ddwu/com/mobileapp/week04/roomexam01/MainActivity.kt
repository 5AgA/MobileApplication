package ddwu.com.mobileapp.week04.roomexam01

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.InvalidationTracker
import ddwu.com.mobileapp.week04.roomexam01.data.Food
import ddwu.com.mobileapp.week04.roomexam01.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week04.roomexam01.ui.FoodViewModel
import ddwu.com.mobileapp.week04.roomexam01.ui.FoodViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    // view binding object
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val foodViewModel: FoodViewModel by viewModels {
        FoodViewModelFactory( (application as FoodApplication).foodRepo )
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

        val adapter = FoodAdapter(ArrayList<Food>())

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.foodRecyclerView.layoutManager = layoutManager
        binding.foodRecyclerView.adapter = adapter

        foodViewModel.allFoods.observe(this, Observer { foods ->
            adapter.foods = foods
            adapter.notifyDataSetChanged()
        })

        // food by country
        binding.btnShow.setOnClickListener {
            val country = binding.etCountry.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val defFoods : Deferred<List<Food>> = foodViewModel.findFoodByCountry(country)
                val foods = defFoods.await()
                for (food in foods) {
                    Log.d(TAG, food.toString())
                }
            }
        }

        binding.btnInsert.setOnClickListener {
            val foodName = binding.etFood.text.toString()
            val countryName = binding.etCountry.text.toString()
            val food = Food(0, foodName, countryName)   // new food

            foodViewModel.addFood(food)
        }

        // update food
        binding.btnUpdate.setOnClickListener {
            val foodName = binding.etFood.text.toString()
            val countryName = binding.etCountry.text.toString()
            val food = Food(0, foodName, countryName)

            foodViewModel.modifyFoodCountryByFood(food)
        }

        // delete food
        binding.btnDelete.setOnClickListener {
            val foodName = binding.etFood.text.toString()
            val food = Food(0, foodName, "")

            foodViewModel.removeFoodByName(food)
        }

    }
}