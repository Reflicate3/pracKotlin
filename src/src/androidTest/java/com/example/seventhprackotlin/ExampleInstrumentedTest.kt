
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.seventhprackotlin.MainActivity
import com.example.seventhprackotlin.R
import com.example.seventhprackotlin.ToastMatcher
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUrlInputIsDisplayed() {
        // Проверяем, что поле ввода URL отображается
        onView(withId(R.id.urlInput)).check(matches(isDisplayed()))
    }

    @Test
    fun testDownloadButtonIsDisplayed() {
        // Проверяем, что кнопка загрузки отображается
        onView(withId(R.id.downloadButton)).check(matches(isDisplayed()))
    }

    @Test
    fun testImageViewIsInitiallyHidden() {
        // Проверяем, что ImageView изначально скрыт
        onView(withId(R.id.imageView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun testDownloadButtonWithoutUrlShowsToast() {
        // Нажимаем на кнопку без ввода URL
        onView(withId(R.id.downloadButton)).perform(click())

        // Проверяем, что отображается Toast с сообщением "Введите URL"
        onView(withText("Введите URL"))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSuccessfulImageDownload() {
        // Используем тестовый URL изображения
        val testUrl = "https://via.placeholder.com/150"

        // Вводим URL и закрываем клавиатуру
        onView(withId(R.id.urlInput)).perform(typeText(testUrl), closeSoftKeyboard())

        // Нажимаем кнопку загрузки
        onView(withId(R.id.downloadButton)).perform(click())

        // Ожидаем загрузки изображения (можно использовать IdlingResource для более точного ожидания)
        Thread.sleep(5000)

        // Проверяем, что ImageView стал видимым
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
    }
}