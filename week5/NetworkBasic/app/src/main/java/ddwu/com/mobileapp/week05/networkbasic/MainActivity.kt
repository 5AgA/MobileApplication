package ddwu.com.mobileapp.week05.networkbasic

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobileapp.week05.networkbasic.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week05.networkbasic.ui.NetworkViewModel
import ddwu.com.mobileapp.week05.networkbasic.ui.NetworkViewModelFactory

class MainActivity : AppCompatActivity() {

    val TAG = "NetworkTest"

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

        // ViewModel 객체 생성
        val netViewModel : NetworkViewModel by viewModels {
            NetworkViewModelFactory ( (application as NetworkApplication).netRepository )
        }

        // ViewModel 을 사용하여 텍스트 결과를 지속 관찰하여 화면에 반영
        netViewModel.textData.observe(this) { resultText ->
            binding.tvDisplay.setText(resultText)
        }


        if (netViewModel.bitmapData != null) {
            // ViewModel 을 사용하여 이미지 결과를 지속 관찰하여 화면에 반영
            netViewModel.bitmapData.observe(this) { resultBitmap ->
                binding.ivDisplay.setImageBitmap(resultBitmap)
            }
        }

        binding.btnConnInfo.setOnClickListener {
            netViewModel.getConnInfo()
        }

        binding.btnActiveInfo.setOnClickListener {
            netViewModel.getOnlineInfo()
        }

        binding.btnDown.setOnClickListener {
            val address = resources.getString(R.string.movie_url) + binding.etData.text.toString()
            netViewModel.getNetworkText(address)
            // netViewModel.getNetworkText(resources.getString(R.string.cs_url))
        }

        binding.btnImg.setOnClickListener {
            // 이미지 요청 resources.getString(R.string.image_url))
            netViewModel.getNetworkImage(resources.getString(R.string.image_url))
        }

        binding.btnSend.setOnClickListener {
            // post 요청 - resources.getString(R.string.server_url) 사용
            val address = resources.getString(R.string.server_url)
            val data = binding.etData.text.toString()
            netViewModel.setNetworkText(address, data)
        }

    }
}