package ddum.com.mobile.week09.naverretrofitsample

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import ddum.com.mobile.week09.naverretrofitsample.data.util.FileManager
import ddum.com.mobile.week09.naverretrofitsample.databinding.ActivityMainBinding
import ddum.com.mobile.week09.naverretrofitsample.ui.BookAdapter
import ddum.com.mobile.week09.naverretrofitsample.ui.NVViewModel
import ddum.com.mobile.week09.naverretrofitsample.ui.NVViewModelFactory
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

class MainActivity : AppCompatActivity() {

    val TAG = "MAIN_ACTIVITY_TAG"

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
            NVViewModelFactory( (application as NVApplication).nvRepository )
        }

        nvViewModel.books.observe(this) { books ->
            adapter.books = books
            adapter.notifyDataSetChanged()
        }

        // bitmap 객체
        nvViewModel.drawable.observe(this) { drawable ->
            binding.imageView.setImageBitmap(drawable)
        }


        // 필요할 경우 파일 디렉토리 생성
        // 내부저장소 전용위치에 images 하위 디렉토리 생성
        if(!FileManager.createSubDirectory(filesDir, "images")) {
            Log.d(TAG, "Sub Directory Error!")
        }

//        val imageFile = File( filesDir, "image.jpg")
//        val bitmap = BitmapFactory.decodeFile(imageFile.path)
//        binding.imageView.setImageBitmap(bitmap)

        Glide.with(this)
            .load("${filesDir}/images/image.jpg")
            .into(binding.imageView)


        // 내부 저장소 : /data/user/0/ddum.com.mobile.week09.naverretrofitsample/files
        Log.d(TAG, "${filesDir}")
        // 내부 캐시 : /data/user/0/ddum.com.mobile.week09.naverretrofitsample/cache
        Log.d(TAG, "${cacheDir}")
        // 외부 저장소 : /storage/emulated/0/Android/data/ddum.com.mobile.week09.naverretrofitsample/files
        Log.d(TAG, "${getExternalFilesDir(null).toString()}")
        // 외부 캐시 : /storage/emulated/0/Android/data/ddum.com.mobile.week09.naverretrofitsample/cache
        Log.d(TAG, "${externalCacheDir}")


        val writeData = "Mobile Application!"

        // 기본 방법 – 전용 위치가 아닌 지정 위치 사용
        val writeFile = File ( filesDir, "test.txt")
        val outputStream = FileOutputStream(writeFile)
        outputStream.write(writeData.toByteArray())
        outputStream.close()
        // Files.write() 대체 사용 가능

        openFileInput("test.txt").bufferedReader().useLines { lines ->
            for (line in lines) {
                Log.d(TAG, line + "\n")
            }
        }


        adapter.setOnItemClickListener(object: BookAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val url = adapter.books?.get(position)?.image

                // 실습1. url 에 해당하는 이미지 바로 표시
                // Glide 내부적으로 별도의 스레드로 처리
//                Glide.with(this@MainActivity) // context 정보 설정
//                    .load(url) // 로딩할 이미지 정보
//                    .into(binding.imageView) // 로딩할 이미지를 보여줄 이미지 뷰 객체


                // 실습2. ViewModel 을 통해 Bitmap 을 가져와 표시
                nvViewModel.setImage(url)

                Glide.with(this@MainActivity) // context
                .asBitmap()
                .load(url)
                    .into( object: CustomTarget<Bitmap>( 350, 350 ) { // or Target.SIZE_ORIGINAL
                        override fun onLoadCleared(placeholder: Drawable?) {
                            Log.d(TAG, "Image Load Cleard!")
                        }
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            val imageFile = File ( "${filesDir}/images", "book.jpg")
                            val fos = FileOutputStream(imageFile)
                            resource.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                            fos.close()
                        }
                    })

                // 실습3. 클릭할 경우 Image 의 url 을 Intent 에 저장(key: url) 후 DetailActivity 호출
                var intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
                finish()
            }
        })



        binding.btnSearch.setOnClickListener{
            val query = binding.etKeyword.text.toString()
            nvViewModel.getBooks(query)
        }


    }
}