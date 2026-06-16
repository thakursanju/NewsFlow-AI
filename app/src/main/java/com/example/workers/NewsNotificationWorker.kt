package com.example.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.example.R
import java.util.concurrent.TimeUnit

class NewsNotificationWorker(
    appContext: Context, 
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        showNotification("NewsFlow AI", "Check out the latest headlines today!")
        return Result.success()
    }

    private fun showNotification(title: String, description: String) {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "daily_news_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Daily News", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)

        manager.notify(1, builder.build())
    }

    companion object {
        fun enqueueWork(context: Context) {
            val workRequest = PeriodicWorkRequestBuilder<NewsNotificationWorker>(1, TimeUnit.DAYS)
                .setConstraints(Constraints.Builder().setRequiresBatteryNotLow(true).build())
                .build()
            
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "DailyNewsNotification",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}
