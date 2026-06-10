package ddwu.com.mobile.week10.alarmtest

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ddwu.com.mobile.week10.alarmtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        createNotificationChannel()
        checkNotificationPermission()

        mainBinding.btnOneShot.setOnClickListener {
            val requestId = 100
            val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, MyBroadcastReceiver ::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(applicationContext, requestId, intent,
                    PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
            manager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 10 * 1000,
                pendingIntent
            )
        }

        mainBinding.btnRepeat.setOnClickListener {

//            val pendingIntent : PendingIntent =
            val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

//            manager.setInexactRepeating(
//                AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
//                AlarmManager.INTERVAL_HOUR,
//                pendingIntent
//            )

        }

        mainBinding.btnStopAlarm.setOnClickListener {

        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test Channel"
            val descriptionText = "Test Channel Message"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("My_Channel_ID", name, importance)
            mChannel.description = descriptionText

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    /*알림 권한 확인*/
    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // 권한이 없는 경우 권한 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    100
                )
            }
        }
    }

    /*권한 요청 결과 확인*/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(applicationContext, "사용권한 승인, 버튼 다시 클릭!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "권한 필요", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}