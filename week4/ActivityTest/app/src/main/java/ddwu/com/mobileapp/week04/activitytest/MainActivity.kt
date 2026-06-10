package ddwu.com.mobileapp.week04.activitytest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ddwu.com.mobileapp.week04.activitytest.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week04.activitytest.ui.NameViewModel

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

        // basic
        binding.button.setOnClickListener {
            binding.tvName.text = "NAME: " + binding.etName.text
        }


        // ViewModel
//        val viewModel = ViewModelProvider(this).get(NameViewModel::class.java)
//        viewModel.getName().observe(this, Observer { newName ->
//            binding.tvName.text = "NAME: " + newName
//        })
//
//        binding.button.setOnClickListener {
//            val inputName = binding.etName.text.toString()
//            viewModel.updateName( inputName )
//        }

    }
}