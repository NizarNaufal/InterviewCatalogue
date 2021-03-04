package id.technicaltest.interviewcatalogue

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.technicaltest.interviewcatalogue.view.HomeActivity
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    //rule test home navbar
    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    private fun waiter() {
        Thread.sleep(2000L)
    }

    @Test
    fun movieFragment() {
        waiter()
        onView(withId(R.id.vp_movie_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.vp_movie_banner)).perform(swipeLeft())
        onView(withId(R.id.nestedScroll_movie)).perform(swipeUp())
    }

    @Test
    fun seriesFragment() {
        onView(withId(R.id.navigation_series)).perform(click())
        waiter()
        onView(withId(R.id.vp_series_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.vp_series_banner)).perform(swipeLeft())
        onView(withId(R.id.nestedScroll_series)).perform(swipeUp())
    }


}