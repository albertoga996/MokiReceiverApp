package com.example.mokireceiverapp.derived

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MyReceiver : BroadcastReceiver() {
    private val workManager by lazy { WorkManager.getInstance() }

    override fun onReceive(context: Context?, intent: Intent?) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Intent().also { startIntent ->
                startIntent.setClassName(
                    "com.example.mokireceiverapp",
                    "com.example.mokireceiverapp.MainActivity"
                )
                startIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startIntent.putExtra("fact", intent?.getStringExtra("fact"))
                context?.startActivity(startIntent)
            }
        } else {
            context?.let {
                val data = Data.Builder()
                data.putString("message", intent?.getStringExtra("fact"))

                workManager.enqueue(
                    OneTimeWorkRequestBuilder<ShowNotificationWorker>()
                        .setInputData(data.build())
                        .setInitialDelay(1, TimeUnit.SECONDS)
                        .build()
                )

            }
        }

    }
}