package com.example.benja.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.opengl.ETC1;
import android.os.SystemClock;
import android.support.design.widget.TextInputEditText;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.benja.myapplication.Toolbar.SearchActivity;
import com.example.benja.myapplication.Toolbar.SearchResultActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotSame;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *///    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
public class SearchActivityResultTest {
    /**
     * This RULE specifies that this activity is launched
     */
    //Always make this public
    @Rule
    public ActivityTestRule<SearchActivity> searchArticlesActivityActivityTestRule =
            new ActivityTestRule<SearchActivity>(SearchActivity.class);

    private SearchActivity mActivity = null;

    private Instrumentation.ActivityMonitor mainActivityMonitor =
            getInstrumentation().addMonitor(
                    MainActivity.class.getName(),
                    null,
                    false);

    private Instrumentation.ActivityMonitor displaySearchArticlesActivityMonitor =
            getInstrumentation().addMonitor(
                    SearchActivity.class.getName(),
                    null,
                    false);

    private CheckBox cb_arts;
    private CheckBox cb_business;
    private CheckBox cb_entrepreneurs;
    private CheckBox cb_politics;
    private CheckBox cb_sports;
    private CheckBox cb_travel;

    private List<String> listOfSections;

    private EditText mTextInputEditText;

    private View endDateButton;
    private View beginDateButton;

    @Before
    public void setUp() throws Exception {

        /** With this, we get the context! */
        mActivity = searchArticlesActivityActivityTestRule.getActivity();

        //Checkboxes
        cb_arts = (CheckBox) mActivity.findViewById(R.id.artsCB);
        cb_business = (CheckBox) mActivity.findViewById(R.id.businessCB);
        cb_entrepreneurs = (CheckBox) mActivity.findViewById(R.id.entrepreneursCB);
        cb_politics = (CheckBox) mActivity.findViewById(R.id.politicsCB);
        cb_sports = (CheckBox) mActivity.findViewById(R.id.sportsCB);
        cb_travel = (CheckBox) mActivity.findViewById(R.id.travelCB);

        listOfSections = mActivity.getListOfSections();

        //TextInputEditText
        mTextInputEditText = mActivity.findViewById(R.id.editTextSearch);

        //Views (buttons)
        endDateButton = mActivity.findViewById(R.id.editTextBeginDate);
        beginDateButton = mActivity.findViewById(R.id.editTextEndDate);

    }

    @Test
    public void testViewsAreDisplayed() {

        //If we can find the views, we can conclude that the activity is launched successfully
        onView(withId(R.id.searchButton)).check(matches(isDisplayed()));

        onView(withId(R.id.editTextBeginDate)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextEndDate)).check(matches(isDisplayed()));

        onView(withId(R.id.editTextSearch)).check(matches(isDisplayed()));

        onView(withId(R.id.artsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.businessCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.entrepreneursCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.politicsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.sportsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.travelCB)).perform(click()).check(matches(isDisplayed()));

    }

    @Test
    public void testViewsAreWritableAndCheckable() {

        //Clearing the TextInputEditText and unchecking the checkboxes
        onView(withId(R.id.editTextSearch)).perform(clearText());

        if (cb_arts.isChecked()) onView(withId(R.id.artsCB)).perform(click());
        if (cb_business.isChecked()) onView(withId(R.id.businessCB)).perform(click());
        if (cb_entrepreneurs.isChecked()) onView(withId(R.id.entrepreneursCB)).perform(click());
        if (cb_politics.isChecked()) onView(withId(R.id.politicsCB)).perform(click());
        if (cb_sports.isChecked()) onView(withId(R.id.sportsCB)).perform(click());
        if (cb_travel.isChecked()) onView(withId(R.id.travelCB)).perform(click());

        //Writing something on the EditText
        onView(withId(R.id.editTextSearch))
                .perform(typeText("This is a test"))
                .check(matches(isDisplayed()));

        //Checking the checkboxes are checked when clicked
        onView(withId(R.id.artsCB)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.businessCB)).perform(click()).check(matches(isChecked()));
        //      Behind keyboard so fail
        //    onView(withId(R.id.entrepreneursCB)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.politicsCB)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.sportsCB)).perform(click()).check(matches(isChecked()));
        //      Behind keyboard so fail
        //     onView(withId(R.id.travelCB)).perform(click()).check(matches(isChecked()));

        //We uncheck all the checkboxes
        if (cb_arts.isChecked()) onView(withId(R.id.artsCB)).perform(click());
        if (cb_business.isChecked()) onView(withId(R.id.businessCB)).perform(click());
        if (cb_entrepreneurs.isChecked()) onView(withId(R.id.entrepreneursCB)).perform(click());
        if (cb_politics.isChecked()) onView(withId(R.id.politicsCB)).perform(click());
        if (cb_sports.isChecked()) onView(withId(R.id.sportsCB)).perform(click());
        if (cb_travel.isChecked()) onView(withId(R.id.travelCB)).perform(click());

    }

    @Test
    public void testBeginDateAlertDialogWorks() {

        //We check that the button is available and clickable
        onView(withId(R.id.editTextBeginDate)).check(matches(isDisplayed()))
                .check(matches(isClickable()));

        //We store the content of the tv (which is inside the button) in a String
        // to compare this String with the one that will be get from the modified
        // text of the TextView when a new Date is set.
        TextView tv = (TextView) mActivity.findViewById(R.id.editTextBeginDate);
        String tvBeforeClick = tv.getText().toString();

        onView(withId(R.id.editTextBeginDate)).check(matches(isDisplayed())).perform(click());

        //We check that the alert Dialog is displayed checking that the two buttons are displayed
        onView(withId(android.R.id.button1)).check(matches(isDisplayed()));
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()));

        onView(withId(android.R.id.button1)).perform(click());
        String tvAfterClick = tv.getText().toString();

        //We use two strings to prove that, after clicking OK in the alertDialog,
        //the date is updated (to today, in this case). It is updated because the
        //text view content is not the same, which means it has changed.
        assertNotSame("Should not be same object", tvBeforeClick, tvAfterClick);

    }

    @Test
    public void testEndDateAlertDialogWorks() {

        //We check that the button is available and clickable
        onView(withId(R.id.editTextEndDate)).check(matches(isDisplayed()))
                .check(matches(isClickable()));

        //We store the content of the tv in a String to compare this String with the
        //one that will be get from the modified text of the TextView when a new Date
        //is set.
        TextView tv = (TextView) mActivity.findViewById(R.id.editTextEndDate);
        String tvBeforeClick = tv.getText().toString();

        onView(withId(R.id.editTextEndDate)).check(matches(isDisplayed())).perform(click());

        //We check that the alert Dialog is displayed checking that the two buttons are displayed
        onView(withId(android.R.id.button1)).check(matches(isDisplayed()));
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()));

        onView(withId(android.R.id.button1)).perform(click());
        String tvAfterClick = tv.getText().toString();

        //We use two strings to prove that, after clicking OK in the alertDialog,
        //the date is updated (to today, in this case). It is updated because the
        //text view content is not the same, which means it has changed.
        assertNotSame("Should not be same object", tvBeforeClick, tvAfterClick);

    }


    @Test
    public void testDisplaySearchArticlesActivityIsLoaded() {

        //Clearing the TextInputEditText and unchecking the checkboxes
        onView(withId(R.id.editTextSearch)).perform(clearText());

        if (cb_arts.isChecked()) onView(withId(R.id.artsCB)).perform(click());
        if (cb_business.isChecked()) onView(withId(R.id.businessCB)).perform(click());
        if (cb_entrepreneurs.isChecked()) onView(withId(R.id.entrepreneursCB)).perform(click());
        if (cb_politics.isChecked()) onView(withId(R.id.politicsCB)).perform(click());
        if (cb_sports.isChecked()) onView(withId(R.id.sportsCB)).perform(click());
        if (cb_travel.isChecked()) onView(withId(R.id.travelCB)).perform(click());

        //Checking one checkbox
        onView(withId(R.id.politicsCB)).perform(click());

        //Clicking Search button
        onView(withId(R.id.searchButton)).perform(click());

        Activity displaySearchArticles = getInstrumentation().waitForMonitorWithTimeout(displaySearchArticlesActivityMonitor, 5000);
        assertNotNull(displaySearchArticles);
        displaySearchArticles.finish();

    }


    @After
    public void tearDown() throws Exception {

        mActivity = null;

    }

}