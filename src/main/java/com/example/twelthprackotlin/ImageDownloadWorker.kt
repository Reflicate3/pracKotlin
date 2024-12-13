package com.example.twelthprackotlin

import android.content.Context
import android.graphics.Bitmap
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.twelthprackotlin.utils.downloadImage
import com.example.twelthprackotlin.utils.saveImage

class ImageDownloadWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val url = inputData.getString("image_url") ?: return Result.failure()

        return try {
            val bitmap = downloadImage(url)
            if (bitmap != null) {
                saveImage(applicationContext, bitmap)
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}