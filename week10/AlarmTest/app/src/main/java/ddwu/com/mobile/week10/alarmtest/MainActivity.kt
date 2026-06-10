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

    val alarmManager : AlarmManager? by lazy {
        getSystemService(ALARM_SERVICE) as AlarmManager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        mainBinding.btnOneShot.setOnClickListener {
            checkNotificationPermission()   // 알림 권한 확인, 승인할 경우 onRequestPermissionsResult() 에서 채널 생성

            /*알람 실행 시 MyBroadcastRecevier 실행, ALARM_MESSAGE 전달*/
            val intent = Intent (this, MyBroadcastReceiver::class.java).apply {
                action = "ddwu.com.mobile.week10.alarmtest.ACTION_ALARM"
                putExtra("ALARM_MESSAGE", "일어나세요!")
            }

            val pendingIntent : PendingIntent
                    = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            /*현재 경과시간 10초 뒤에 일회성 알람 실행*/
            alarmManager?.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 10 * 1000,
                pendingIntent)
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

    /*알림 채널 생성 - 알람 권한 요청 후 요청 승인이 됐을 경우 실행*/
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Notification Channel 의 생성
            val name = "Alarm Channel"
            val descriptionText = "Alarm Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(resources.getString(R.string.channel_id), name, importance)
            mChannel.description = descriptionText

            // Channel 을 시스템에 등록, 등록 후에는 중요도 변경 불가
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
            Toast.makeText(applicationContext, "${notificationManager.areNotificationsEnabled()}", Toast.LENGTH_SHORT).show()
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
                    createNotificationChannel()     // 권한 승인 후 채널 생성
                } else {
                    Toast.makeText(applicationContext, "권한 필요", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}