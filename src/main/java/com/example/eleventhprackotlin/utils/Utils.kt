package com.example.eleventhprackotlin.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URL

suspend fun downloadImage(url: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val inputStream: InputStream = URL(url).openStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

suspend fun saveImage(context: Context, bitmap: Bitmap): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val directory = context.filesDir
            val file = File(directory, "downloaded_image_${System.currentTimeMillis()}.jpg")
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

suspend fun loadSavedImages(context: Context): List<Bitmap> {
    return withContext(Dispatchers.IO) {
        val images = mutableListOf<Bitmap>()
        val directory = context.filesDir
        val files = directory.listFiles { file -> file.extension == "jpg" }
        files?.forEach { file ->
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            images.add(bitmap)
        }
        images
    }
}