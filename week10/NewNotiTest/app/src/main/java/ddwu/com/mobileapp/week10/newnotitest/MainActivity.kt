package ddwu.com.mobileapp.week10.newnotitest

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.security.identity.PersonalizationData
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobileapp.week10.newnotitest.databinding.ActivityMainBinding
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val channelID by lazy {
        resources.getString(R.string.channel_id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        createNotificationChannel()

        mainBinding.btnNoti.setOnClickListener {
            Thread {
                sleep(3000)
                showNotification()
            }.start()

            showNotification()
        }

        mainBinding.btnNotiAction.setOnClickListener {
            Thread {
                sleep(3000)
                showNotificationWithAction()
            }.start()
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Notification Channel 의 생성
            val name = "Test Channel"
            val descriptionText = "Test Channel Message"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(channelID, name, importance)
            mChannel.description = descriptionText

            // Channel 을 시스템에 등록, 등록 후에는 중요도 변경 불가
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
            Toast.makeText(applicationContext, "${notificationManager.areNotificationsEnabled()}", Toast.LENGTH_SHORT).show()

//            notificationManager.deleteNotificationChannel(channelID)    // 채널 삭제
        }
    }


    private fun showNotification() {
        checkNotificationPermission()
        val intent = Intent(this, AlertActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent
            = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        var newNoti = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("알림 제목")
            .setContentText("짧은 알림 내용")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("확장시 확인할 수 있는 긴 알림 내용"))
            // 8.0이상에서는 대신 Channel 중요도로 설정
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notiManager = NotificationManagerCompat.from(this)
        notiManager.notify(100, newNoti.build())

    }


    private fun showNotificationWithAction() {
        val intent = Intent(this, AlertBroadcastReceiver::class.java).apply {
            action = "ACTION_SNOOZE"
            putExtra("NOTI_ID", 200)
        }
        val pendingIntent: PendingIntent
                = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        var builder = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("알림 제목")
            .setContentText("짧은 알림 내용")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("확장시 확인할 수 있는 긴 알림 내용"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 8.0이상에서는 대신 Channel 중요도로 설정
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(R.drawable.ic_stat_name, "쉬기", pendingIntent)
        val notiManager = NotificationManagerCompat.from(this)
        notiManager.notify(100, builder.build())
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