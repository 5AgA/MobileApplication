package ddwu.com.mobile.week10.alarmtest

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyBroadcastReceiver : BroadcastReceiver(){
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MyBr", "Alarm!!")

        val requestId = 101
        val newIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, requestId, newIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

        val noti = NotificationCompat.Builder(context!!, "My_Channel_ID")
            .setSmallIcon(R.drawable.ic_stat_name_foreground)
            .setContentTitle("기상 시간")
            .setContentText("일어날 시간이야 !")
            .setStyle(NotificationCompat.BigTextStyle().bigText("일어나서 공부하자"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notiManager = NotificationManagerCompat.from(context)
        notiManager.notify(requestId, noti.build())
    }

}