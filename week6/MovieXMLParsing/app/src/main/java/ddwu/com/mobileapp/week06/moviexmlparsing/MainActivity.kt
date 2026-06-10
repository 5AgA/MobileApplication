package ddwu.com.mobileapp.week06.moviexmlparsing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobileapp.week06.moviexmlparsing.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week06.moviexmlparsing.ui.MXPViewModelFactory
import ddwu.com.mobileapp.week06.moviexmlparsing.ui.MovieAdapter
import ddwu.com.mobileapp.week06.moviexmlparsing.ui.MXPViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var adapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val mxpViewModel : MXPViewModel by viewModels {
            MXPViewModelFactory( (application as MXPApplication).networkRepo )
        }

        adapter = MovieAdapter()
        binding.rvMovies.adapter = adapter
        binding.rvMovies.layoutManager = LinearLayoutManager(this)


        mxpViewModel.movies.observe(this) { movies ->
            adapter.items = movies
            adapter.notifyDataSetChanged()
        }

        binding.btnSearch.setOnClickListener {
            mxpViewModel.showDailyBoxOffice( binding.etData.text.toString() )
        }

    }
}