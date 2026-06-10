package ddwu.com.mobileapp.week06.naverparsing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobileapp.week06.naverparsing.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week06.naverparsing.ui.BookAdapter
import ddwu.com.mobileapp.week06.naverparsing.ui.NXPViewModel
import ddwu.com.mobileapp.week06.naverparsing.ui.NXPViewModelFactory

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var adapter : BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val xpViewModel : NXPViewModel by viewModels {
            NXPViewModelFactory( (application as NXPApplication).networkRepo )
        }

        adapter = BookAdapter()
        binding.rvBooks.adapter = adapter
        binding.rvBooks.layoutManager = LinearLayoutManager(this)

        xpViewModel.books.observe(this) { books ->
            adapter.books = books
            adapter.notifyDataSetChanged()
        }

        binding.btnSearch.setOnClickListener {
            xpViewModel.showBooksByKeyword( binding.etData.text.toString() )
        }

    }
}