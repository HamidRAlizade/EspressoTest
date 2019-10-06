package com.hamidreza.alizade.esperssotest.intentsBasicSample

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.hamidreza.alizade.esperssotest.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DialerActivityTest{
    @get:Rule
    val intentsTestRule = IntentsTestRule(DialerActivity::class.java)

    @Test fun activityResult_DisplaysContactsPhoneNumber() {
        // Build the result to return when the activity is launched.
        val resultData = Intent()
        val phoneNumber = "123-345-6789"
        resultData.putExtra("phone", phoneNumber)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
//        intending(toPackage("android.intent.action.CALL")).respondWith(result)

        // User action that results in "contacts" activity being launched.
        // Launching activity expects phoneNumber to be returned and displayed.
        onView(withId(R.id.edit_text_caller_number)).perform(typeText(phoneNumber))
        onView(withId(R.id.button_call_number)).perform(click())

        // Assert that the data we set up above is shown.
    }

}