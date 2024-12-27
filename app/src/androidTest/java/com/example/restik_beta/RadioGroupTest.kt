package com.example.restik_beta

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RadioGroupTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CartActivity::class.java)

    @Test
    fun testPaymentMethodSelection() {
        // Запускаем активность
        ActivityScenario.launch(CartActivity::class.java)

        // Выбираем оплату наличными
        onView(withId(R.id.payment_cash)).perform(click())

        // Проверяем, что выбран метод "Наличными"
        onView(withId(R.id.payment_cash)).check(matches(isChecked()))

        // Проверяем, что метод "Картой" не выбран
        onView(withId(R.id.payment_card)).check(matches(isNotChecked()))
    }
}