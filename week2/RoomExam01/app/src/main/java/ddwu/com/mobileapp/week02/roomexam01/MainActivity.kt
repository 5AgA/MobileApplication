package ddwu.com.mobileapp.week02.roomexam01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import ddwu.com.mobileapp.week02.roomexam01.data.Food
import ddwu.com.mobileapp.week02.roomexam01.data.FoodDao
import ddwu.com.mobileapp.week02.roomexam01.data.FoodDatabase
import ddwu.com.mobileapp.week02.roomexam01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var foodDao : FoodDao

    val foodDB by lazy {
        FoodDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Database 작업


//        RecylcerView 설정
        val adapter = FoodAdapter( /*DB에서 읽어온 food list 지정*/ )

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}