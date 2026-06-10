package ddwu.com.mobile.week10.alarmtest

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        /*전달 받은 intent 에서 Message 확인*/
        val alarmMessage= intent?.getStringExtra("ALARM_MESSAGE")
        Log.d("MyBroadcastReceiver", "ALARM_MESSAGE: ${alarmMessage}")

        /*알림 클릭 시 MainActivity 실행을 위한 Intent 준비*/
        val newIntent = Intent (context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent : PendingIntent
                = PendingIntent.getActivity(context, 0, newIntent, PendingIntent.FLAG_IMMUTABLE)

        val channelID = context?.resources?.getString(R.string.channel_id)!!

        /*알림 표시*/
        val alarmNotiBuilder = NotificationCompat.Builder(context!!, channelID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("알람!!!")
            .setContentText(alarmMessage)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notiManager = NotificationManagerCompat.from(context)
        notiManager.notify(100, alarmNotiBuilder.build())

    }

}