package ddwu.com.mobile.photomemo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import ddwu.com.mobile.photomemo.data.MemoDao
import ddwu.com.mobile.photomemo.data.MemoDatabase
import ddwu.com.mobile.photomemo.data.MemoDto
import ddwu.com.mobile.photomemo.databinding.ActivityAddMemoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class AddMemoActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    val addMemoBinding by lazy {
        ActivityAddMemoBinding.inflate(layoutInflater)
    }

    val memoDB: MemoDatabase by lazy {
        MemoDatabase.getDatabase(this)
    }

    val memoDao: MemoDao by lazy {
        memoDB.memoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(addMemoBinding.root)

        /*DB 에 memoDto 저장*/
        addMemoBinding.btnAdd.setOnClickListener {
            if (currentPhotoFileName != null) {
                val memo = addMemoBinding.tvAddMemo.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    memoDao.insertMemo(MemoDto(0, currentPhotoFileName!!, memo))
                }

                Toast.makeText(this@AddMemoActivity, "New memo is added!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // 카메라 앱을 실행하는 기능 구현
        addMemoBinding.ivAddPhoto.setOnClickListener {
//            Toast.makeText(this@AddMemoActivity, "Click! Implement call taking a picture", Toast.LENGTH_SHORT).show()
        }

        addMemoBinding.btnCancel.setOnClickListener {
            finish()
        }
    }

    lateinit var currentPhotoPath: String   // 현재 이미지 파일의 경로 저장
    var currentPhotoFileName: String? = null    // 현재 이미지 파일명

    /*카메라 앱 호출 관련 기능 구현*/

}