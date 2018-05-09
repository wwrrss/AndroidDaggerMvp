package willermo.me.dependency

import android.support.test.espresso.Espresso
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.util.Log
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import willermo.me.dependency.activities.MainActivity
import java.util.*

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    var valueToSave = "TEST_VALUE"


    @Before
    fun setup(){


    }


    @Test
    fun shouldSaveValueToSharedPreferences(){
        valueToSave = UUID.randomUUID().toString()
        onView(withId(R.id.mainActivityMyValueInput)).perform(replaceText(valueToSave))
        onView(withId(R.id.mainAcivitySaveButton)).perform(click())
        onView(withId(R.id.mainAcivityMyValueTextView)).check(matches(withText(valueToSave)))
    }

    @Test
    fun recyclerViewShouldHave100Items(){
        onView(withId(R.id.mainActivityPostRecycler)).check(matches(CustomRecylcerMatcher.withItemCount(100)))
    }

}