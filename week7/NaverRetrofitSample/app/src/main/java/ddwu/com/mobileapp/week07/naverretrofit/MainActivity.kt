package ddwu.com.mobileapp.week07.naverretrofit

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobileapp.week07.naverretrofit.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week07.naverretrofit.ui.BookAdapter
import ddwu.com.mobileapp.week07.naverretrofit.ui.NVViewModel
import ddwu.com.mobileapp.week07.naverretrofit.ui.NVViewModelFactory

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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

        val adapter = BookAdapter()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvBooks.layoutManager = layoutManager
        binding.rvBooks.adapter = adapter

        val nvViewModel : NVViewModel by viewModels {
            NVViewModelFactory( (application as NVApplication).nvRepository)
        }

        nvViewModel.books.observe(this) { books ->
            adapter.books = books
            adapter.notifyDataSetChanged()
        }

//        nvViewModel.bitmap.observe(this) { bitmap ->
//            binding.imageView.setImageBitmap(bitmap)
//        }


//        adapter.setOnItemClickListener(object: BookAdapter.OnItemClickListener {
//            override fun onItemClick(view: View, position: Int) {
//                val url = adapter.books?.get(position)?.image
//                nvViewModel.setImage(url)
//            }
//        })

        binding.btnSearch.setOnClickListener{
            val query = binding.etKeyword.text.toString()
            val id = resources.getString(R.string.client_id)  // strings.xml에 자신이 등록한 Naver client ID 저장
            val secret = resources.getString(R.string.client_secret)    // strings.xml에 자신이 등록한 Naver client secret 저장
            nvViewModel.getBooks(query, id, secret)
        }

    }
}