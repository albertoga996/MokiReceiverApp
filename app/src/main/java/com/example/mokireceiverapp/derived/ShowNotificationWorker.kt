package com.example.mokireceiverapp.derived

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mokireceiverapp.MainActivity
import com.example.mokireceiverapp.R

private const val NOTIF_ID = 1337
private const val CHANNEL_MOKI = "MokiMoki"

class ShowNotificationWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun doWork(): Result {
        val contentIntent = Intent(appContext, MainActivity::class.java)
        contentIntent.putExtra("fact", inputData.getString("message"))
        contentIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pi = PendingIntent.getActivity(
            appContext,
            0,
            contentIntent,
            0
        )

        val builder = NotificationCompat.Builder(appContext, CHANNEL_MOKI)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Open receiver App")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pi)

        val mgr = appContext.getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
            && mgr.getNotificationChannel(CHANNEL_MOKI) == null
        ) {
            mgr.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_MOKI,
                    "Moki",
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }

        mgr.notify(NOTIF_ID, builder.build())

        return Result.success()
    }
}
