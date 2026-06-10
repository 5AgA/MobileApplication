package ddwu.com.mobileapp.week10.newnotitest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobileapp.week10.newnotitest.databinding.ActivityAlertBinding

class AlertActivity : AppCompatActivity() {

    val alertBinding by lazy {
        ActivityAlertBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(alertBinding.root)

        alertBinding.btnAlertClose.setOnClickListener {
            finish()
        }
    }
}