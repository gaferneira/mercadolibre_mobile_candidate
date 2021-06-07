package com.mercadolibre.mobile.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import com.mercadolibre.mobile.BaseEspressoTest
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.ui.home.ProductsAdapter
import com.mercadolibre.mobile.utils.CustomMatchers.waitViewIsDisplayed
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest : BaseEspressoTest() {

    @get:Rule
    var permissionFineLocationRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @get:Rule
    var permissionCoarseLocationRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_COARSE_LOCATION)

    @Test
    fun checkSearchLabelChanges() {

        //Check initial view
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

    @Test
    fun checkEmptyView() {

        waitViewIsDisplayed(withId(R.id.container_get_started))

        //Go to search view
        onView(withId(R.id.edit_text_search)).perform(click())

        //Do search
        val query = "aslkfja slkjfaskdl jfaklsjf laks"
        onView(withId(R.id.edit_text_query)).perform(replaceText(query), pressImeActionButton())

        //Check that the empty view is showed
        waitViewIsDisplayed(withId(R.id.container_empty_view))

    }

    @Test
    fun checkGoToDetailsView() {

        waitViewIsDisplayed(withId(R.id.container_get_started))

        //Go to search view
        onView(withId(R.id.edit_text_search)).perform(click())

        //Do search
        val query = "Samsung"
        onView(withId(R.id.edit_text_query)).perform(replaceText(query), pressImeActionButton())

        //Go to details
        waitViewIsDisplayed(withId(R.id.image_view_thumbnail))
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ProductsAdapter.ProductsViewHolder>(0, click()))

        //Check that details page is showed
        waitViewIsDisplayed(withId(R.id.button_purchase))

    }

}
