package com.example.restik_beta

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CartActivityTest {

    @Test
    fun testUIElementsVisibility() {
        // Запускаем активность
        ActivityScenario.launch(CartActivity::class.java)

        // Проверяем отображение RecyclerView
        onView(withId(R.id.cart_recycler_view)).check(matches(isDisplayed()))

        // Проверяем отображение поля для ввода номера стола
        onView(withId(R.id.et_table_number)).check(matches(isDisplayed()))

        // Проверяем отображение итоговой суммы
        onView(withId(R.id.total_price_text)).check(matches(isDisplayed()))

        // Проверяем отображение кнопки "Оформить заказ"
        onView(withId(R.id.checkout_button)).check(matches(isDisplayed()))

        // Проверяем отображение методов оплаты (RadioGroup)
        onView(withId(R.id.payment_method_group)).check(matches(isDisplayed()))
    }
}