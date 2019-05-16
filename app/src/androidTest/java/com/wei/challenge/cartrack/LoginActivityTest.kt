package com.wei.challenge.cartrack

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(LoginActivity::class.java)


    @Test
    fun successfulLogin() {
        val scenario =
            ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.user_input_edit)).perform(typeText("test_user"),closeSoftKeyboard())
        onView(withId(R.id.password_input_edit))
            .perform(typeText("correct_Password@23"),closeSoftKeyboard())
        onView(withId(R.id.login_btn)).perform(click())

        intended(hasComponent(DetailActivity::class.java.canonicalName))

    }
}