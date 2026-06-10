package ddwu.com.mobileapp.week07.retrofittest

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ddwu.com.mobileapp.week07.retrofittest.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week07.retrofittest.ui.RefViewModel
import ddwu.com.mobileapp.week07.retrofittest.ui.RefViewModelFactory

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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

        val refViewModel : RefViewModel by viewModels {
            RefViewModelFactory( (application as RefApplication).refRepository )
        }


        refViewModel.movies.observe(this, {
            dailyBoxOfficeList ->
            dailyBoxOfficeList?.forEach { movie ->
                Log.d(TAG, movie.toString())
            }
        })


        binding.btnMovie.setOnClickListener {
            val key = getString(R.string.key)    // 자신의 키로 변경 strings.xml 에 저장할 것
            val date = "20241209"
            refViewModel.getMovies(key, date)
        }





    }
}