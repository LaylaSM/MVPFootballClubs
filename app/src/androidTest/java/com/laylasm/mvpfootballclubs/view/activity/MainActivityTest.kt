package com.laylasm.mvpfootballclubs.view.activity


import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.laylasm.mvpfootballclubs.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun mainActivityTest() {
        onView(withId(spinnerPrevMatch))
                .check(matches(isDisplayed()))
        onView(withId(spinnerPrevMatch)).perform(click())
        onView(withText("Spanish La Liga")).perform(click())
        delay()
        onView(withText("Valencia")).check(matches(isDisplayed()))
        delay()
        onView(withText("Valencia")).perform(click())

        onView(withId(team_menu_fav)).check(matches(isDisplayed()))
        onView(withId(team_menu_fav)).perform(click())
        delay()

        Espresso.pressBack()


        onView(withId(spinnerPrevMatch)).perform(swipeLeft())

        onView(withId(spinnerNextMatch)).check(matches(isDisplayed()))
        onView(withId(spinnerNextMatch)).perform(click())
        onView(withText("English Premier League")).perform(click())
        delay()


        Espresso.pressBack()

        delay()
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        delay()
        onView(withId(nextMatch)).perform(click())
        delay()
        onView(withText("English Premier League")).perform(click())
        delay()

        Espresso.pressBack()

        delay()
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        delay()
        onView(withId(favMatch)).perform(click())
        delay()
        onView(withId(viewPagerMatch)).perform(swipeLeft())
        delay()
    }

    private fun delay() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
