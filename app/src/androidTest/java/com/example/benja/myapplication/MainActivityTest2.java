package com.example.benja.myapplication;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.benja.myapplication.Toolbar.NotificationActivity;
import com.example.benja.myapplication.Toolbar.SearchResultActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *///    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;



    @Before
    public void setUp() {

        /** With this, we get the context! */
        mActivity = mainActivityActivityTestRule.getActivity();

        assertThat(mActivity, notNullValue());



    }

    @Test
    public void testSwipePage() {

        onView(withId(R.id.container))
                .check(matches(isDisplayed()));

        onView(withId(R.id.container))
                .perform(swipeLeft());

    }


    @Test
    public void testCheckTabLayoutIsDisplayed() {

        onView(withId(R.id.tabs))
                .perform(click())
                .check(matches(isDisplayed()));

    }
    @Test
    public void testCheckTabSwitch() {

        onView(withText("MOST POPULAR STORIES")).perform(click()).check(matches(isSelected()));
        SystemClock.sleep(800); //Need some time for the articles to load

        onView(withText("TOP STORIES")).perform(click()).check(matches(isSelected()));
        SystemClock.sleep(800); //Need some time for the articles to load

        onView(withText("CUSTOM STORIES")).perform(click()).check(matches(isSelected()));
        SystemClock.sleep(800); //Need some time for the articles to load



    }

    @Test
    public void testNavigationDrawer(){
        onView(withId(R.id.drawerLayout)).perform(swipeUp());

    }


    @After
    public void tearDown() {

        mActivity = null;

    }

}