// MainActivityTest.kt
package com.example.seventhprackotlin

//import android.widget.ImageView
//import io.mockk.*
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.robolectric.Robolectric
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
//import java.io.ByteArrayInputStream
//import java.io.File
//import java.io.IOException
//import java.net.URL
//
//@RunWith(RobolectricTestRunner::class)
//@Config(sdk = [33], manifest = "src/main/AndroidManifest.xml")
//class MainActivityTest {
//
//    private lateinit var activity: MainActivity
//
//    @Before
//    fun setUp() {
//        activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
//    }
//
//    @Test
//    fun testSaveImageSuccess() {
//        // Тест успешного сохранения изображения
//        val imageBytes = ByteArray(10) // Пример данных
//        val result = activity.saveImage(imageBytes, activity.filesDir)
//        assertTrue("Изображение должно быть сохранено успешно", result)
//    }
//
//    @Test
//    fun testSaveImageFailure() {
//        // Тест неудачного сохранения изображения
//        val imageBytes = ByteArray(10)
//        val directory = File("/invalid/path") // Некорректный путь
//        val result = activity.saveImage(imageBytes, directory)
//        assertFalse("Сохранение изображения должно быть неуспешным", result)
//    }
//
//    @Test
//    fun testDownloadImageWithValidUrl() {
//        // Мокируем сетевой вызов и методы UI
//        mockkStatic("java.net.URL")
//        val urlMock = mockk<URL>()
//        every { URL(any()).openStream() } returns ByteArrayInputStream(ByteArray(10))
//
//        // Мокируем метод runOnUiThread
//        mockkObject(activity)
//        every { activity.runOnUiThread(any()) } answers {
//            firstArg<Runnable>().run()
//        }
//
//        activity.downloadImage("https://example.com/image.jpg")
//
//        // Проверяем, что ImageView стал видимым
//        assertEquals("ImageView должен быть видимым", ImageView.VISIBLE, activity.imageView.visibility)
//
//        unmockkAll()
//    }
//
//    @Test
//    fun testDownloadImageWithInvalidUrl() {
//        // Мокируем сетевой вызов, чтобы он бросал исключение
//        mockkStatic("java.net.URL")
//        every { URL(any()).openStream() } throws IOException("Invalid URL")
//
//        // Мокируем Toast
//        mockkStatic(android.widget.Toast::class)
//        every { android.widget.Toast.makeText(any(), any<String>(), any()) } returns mockk()
//
//        activity.downloadImage("invalid_url")
//
//        // Проверяем, что был вызван Toast с сообщением об ошибке
//        verify { android.widget.Toast.makeText(any(), "Ошибка загрузки изображения", any()) }
//
//        unmockkAll()
//    }
//
//    @Test
//    fun testUrlInputIsEmpty() {
//        activity.urlInput.setText("")
//        activity.downloadButton.performClick()
//
//        // Мокируем Toast
//        mockkStatic(android.widget.Toast::class)
//        every { android.widget.Toast.makeText(any(), any<String>(), any()) } returns mockk()
//
//        // Проверяем, что Toast был вызван с нужным сообщением
//        verify { android.widget.Toast.makeText(any(), "Введите URL", any()) }
//
//        unmockkAll()
//    }
//}
