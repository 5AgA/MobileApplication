package ddum.com.mobile.week09.naverretrofitsample

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ddum.com.mobile.week09.naverretrofitsample.data.util.FileManager
import ddum.com.mobile.week09.naverretrofitsample.databinding.ActivityDetailBinding
import ddum.com.mobile.week09.naverretrofitsample.ui.NVViewModel
import ddum.com.mobile.week09.naverretrofitsample.ui.NVViewModelFactory
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

class DetailActivity : AppCompatActivity() {

    val detailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(detailBinding.root)

        // MainActivity 로부터 전달받은 이미지의 URL
        imageUrl = intent.getStringExtra("url")

        val nvViewModel : NVViewModel by viewModels {
            NVViewModelFactory( (application as NVApplication).nvRepository )
        }

        nvViewModel.drawable.observe(this) { drawable ->
            detailBinding.ivBookCover.setImageBitmap(drawable)
        }

        nvViewModel.setImage(imageUrl)

        var time = FileManager.getCurrentTime()

        detailBinding.btnSave.setOnClickListener {

            Glide.with(this) // context
                .asBitmap()
                .load(imageUrl)
                .into( object: CustomTarget<Bitmap>( 350, 350 ) { // or Target.SIZE_ORIGINAL
                    override fun onLoadCleared(placeholder: Drawable?) {
                        Log.d(TAG, "Image Load Cleard!")
                    }
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        time = FileManager.getCurrentTime()
                        val imageFile = File ( "${filesDir}/images", time + ".jpg")
                        val fos = FileOutputStream(imageFile)
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                        fos.close()
                    }
                })
        }

        detailBinding.btnRead.setOnClickListener {
            Glide.with(this)
                .load("${filesDir}/images/" + time + ".jpg")
                .into(detailBinding.ivBookCover)
        }

        detailBinding.btnInit.setOnClickListener {
            detailBinding.ivBookCover.setImageResource(android.R.drawable.sym_def_app_icon)
        }

        detailBinding.btnRemove.setOnClickListener {
            val deleteFile = File ("${filesDir}/images/" + time + ".jpg")
            deleteFile.delete()
        }
    }
}