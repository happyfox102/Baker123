package com.example.restik_beta

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testTableNumberInput() {
        // Запускаем активность
        ActivityScenario.launch(CartActivity::class.java)

        // Вводим номер стола
        val tableNumber = "12"
        onView(withId(R.id.et_table_number)).perform(typeText(tableNumber))

        // Проверяем, что текст был введен
        onView(withId(R.id.et_table_number)).check(matches(withText(tableNumber)))
    }
}
