package com.mercadolibre.mobile.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.mercadolibre.mobile.BaseEspressoTest
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.utils.CustomMatchers.waitViewIsDisplayed
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest : BaseEspressoTest() {

    @Test
    fun checkSearchLabelChanges() {

        waitViewIsDisplayed(withId(R.id.container_get_started))

        //Go to search view
        onView(withId(R.id.edit_text_search)).perform(click())

        //Do search
        val query = "Samsung"
        onView(withId(R.id.edit_text_query)).perform(replaceText(query), pressImeActionButton())

        //Go back to home page
        waitViewIsDisplayed(withId(R.id.loading_progress_bar))

        // Check that the search label was changed.
        onView(withId(R.id.edit_text_search)).check(matches(withText(query)))

    }
}