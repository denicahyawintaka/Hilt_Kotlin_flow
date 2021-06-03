package com.example.myapplication.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.R
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.movieProgressBar))
            .check(matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.movieProgressBar))
            .check(matches(not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.rvMovie))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                18
            )
        )
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.movieProgressBar))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withText(R.string.tvshow_tab)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.tvShowProgressBar))
            .check(matches(not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.rvTvShow))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                18
            )
        )
    }

    @Test
    fun loadMoviesDetail() {
        onView(withId(R.id.movieProgressBar))
            .check(matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.movieProgressBar))
            .check(matches(not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )

        onView(withId(R.id.releaseDateHeader)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.releaseDateHeader)).check(matches(withText(R.string.movie_release)))
        onView(withId(R.id.releaseDate)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.releaseDate)).check(matches(withText(not(""))))

        onView(withId(R.id.ratingHeader)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.ratingHeader)).check(matches(withText(R.string.movie_rating)))
        onView(withId(R.id.rating)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rating)).check(matches(withText(not(""))))

        onView(withId(R.id.popularityHeader)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.popularityHeader)).check(matches(withText(R.string.movie_popularity)))
        onView(withId(R.id.popularity)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(withText(not(""))))

        onView(withId(R.id.overviewHeader)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.overviewHeader)).check(matches(withText(R.string.movie_overview)))
        onView(withId(R.id.overview)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(not(""))))
    }

    @Test
    fun loadTvShowDetail() {
        Thread.sleep(2000)
        onView(withText(R.string.tvshow_tab)).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(2000)
        onView(withId(R.id.releaseDateHeader)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.releaseDateHeader)).check(matches(withText(R.string.movie_release)))
        onView(withId(R.id.releaseDate)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.releaseDate)).check(matches(withText(not(""))))

        onView(withId(R.id.ratingHeader)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.ratingHeader)).check(matches(withText(R.string.movie_rating)))
        onView(withId(R.id.rating)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rating)).check(matches(withText(not(""))))

        onView(withId(R.id.popularityHeader)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.popularityHeader)).check(matches(withText(R.string.movie_popularity)))
        onView(withId(R.id.popularity)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(withText(not(""))))

        onView(withId(R.id.overviewHeader)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.overviewHeader)).check(matches(withText(R.string.movie_overview)))
        onView(withId(R.id.overview)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(not(""))))
    }
}
