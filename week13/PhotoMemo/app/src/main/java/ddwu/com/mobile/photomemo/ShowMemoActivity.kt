package ddwu.com.mobile.photomemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import ddwu.com.mobile.photomemo.data.MemoDto
import ddwu.com.mobile.photomemo.databinding.ActivityShowMemoBinding
import java.io.File

class ShowMemoActivity : AppCompatActivity() {

    val TAG = "ShowMemoActivityTag"

    val showMemoBinding by lazy {
        ActivityShowMemoBinding.inflate(layoutInflater)
    }

    lateinit var memoDto : MemoDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(showMemoBinding.root)

        showMemoBinding.btnModify.setOnClickListener {
            Toast.makeText(this, "Implement modifying data", Toast.LENGTH_SHORT).show()
        }

        showMemoBinding.btnClose.setOnClickListener {
            finish()
        }

        /*전달받은 intent 에서 memoDto 를 읽어온 후 각 view 에 지정*/

    }
}