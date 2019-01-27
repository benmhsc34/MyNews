package com.example.benja.myapplication;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import com.example.benja.myapplication.Toolbar.NotificationActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *///    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
public class NotificationResultTest {
    /**
     * This RULE specifies that this activity is launched
     */
    //Always make this public
    @Rule
    public ActivityTestRule<NotificationActivity> mNotificationActivityActivityTestRule =
            new ActivityTestRule<NotificationActivity>(NotificationActivity.class);

    private NotificationActivity mActivity = null;

    private CheckBox cb_arts;
    private CheckBox cb_business;
    private CheckBox cb_entrepreneurs;
    private CheckBox cb_politics;
    private CheckBox cb_sports;
    private CheckBox cb_travel;

    private Switch notif_switch;


    @Before
    public void setUp() throws Exception {

        mActivity = mNotificationActivityActivityTestRule.getActivity();

        //Checkboxes
        cb_arts = mActivity.findViewById(R.id.artsCB);
        cb_business = mActivity.findViewById(R.id.businessCB);
        cb_entrepreneurs = mActivity.findViewById(R.id.entrepreneursCB);
        cb_politics = mActivity.findViewById(R.id.politicsCB);
        cb_sports = mActivity.findViewById(R.id.sportsCB);
        cb_travel = mActivity.findViewById(R.id.travelCB);

        List<String> listOfSections = mActivity.getNotificationListOfSections();

        //TextInputEditText
        EditText textInputEditText = mActivity.findViewById(R.id.editTextSearchNotification);

        notif_switch = mActivity.findViewById(R.id.notificationSwitch);


    }

    @Test
    public void testSwitchCanOnlyBeActiveWhenCheckboxClicked () {

        //We uncheck the checkboxes that might be checked
        if (cb_arts.isChecked()) onView(withId(R.id.artsCB)).perform(click());
        if (cb_business.isChecked()) onView(withId(R.id.businessCB)).perform(click());
        if (cb_entrepreneurs.isChecked()) onView(withId(R.id.entrepreneursCB)).perform(click());
        if (cb_politics.isChecked()) onView(withId(R.id.politicsCB)).perform(click());
        if (cb_sports.isChecked()) onView(withId(R.id.sportsCB)).perform(click());
        if (cb_travel.isChecked()) onView(withId(R.id.travelCB)).perform(click());

        //We check switch is not enabled
        onView(withId(R.id.notificationSwitch)).check(matches((isEnabled())));

        //We check a checkbox and check the switch is enabled and checkable
        onView(withId(R.id.travelCB)).perform(click());
        onView(withId(R.id.notificationSwitch)).check(matches((isEnabled())));
        onView(withId(R.id.notificationSwitch)).perform(click());
        onView(withId(R.id.notificationSwitch)).check(matches((isChecked())));

        //We uncheck the checkbox and check the checkbox is disabled
        onView(withId(R.id.travelCB)).perform(click());
        onView(withId(R.id.notificationSwitch)).check(matches((isEnabled())));

    }



    @Test
    public void testAllViewsAreDisplayedAndOrClickable () {

        //We uncheck the checkboxes that might be checked
        if (cb_arts.isChecked()) onView(withId(R.id.artsCB)).perform(click());
        if (cb_business.isChecked()) onView(withId(R.id.businessCB)).perform(click());
        if (cb_entrepreneurs.isChecked()) onView(withId(R.id.entrepreneursCB)).perform(click());
        if (cb_politics.isChecked()) onView(withId(R.id.politicsCB)).perform(click());
        if (cb_sports.isChecked()) onView(withId(R.id.sportsCB)).perform(click());
        if (cb_travel.isChecked()) onView(withId(R.id.travelCB)).perform(click());

        //We check all the different views
        onView(withId(R.id.editTextSearchNotification)).check(matches(isDisplayed()));

        onView(withId(R.id.artsCB)).check(matches(isClickable()));
        onView(withId(R.id.businessCB)).check(matches(isClickable()));
        onView(withId(R.id.entrepreneursCB)).check(matches(isClickable()));
        onView(withId(R.id.politicsCB)).check(matches(isClickable()));
        onView(withId(R.id.sportsCB)).check(matches(isClickable()));
        onView(withId(R.id.travelCB)).check(matches(isClickable()));



        //We leave all the checkboxes unchecked
        if (cb_arts.isChecked()) onView(withId(R.id.artsCB)).perform(click());
        if (cb_business.isChecked()) onView(withId(R.id.businessCB)).perform(click());
        if (cb_entrepreneurs.isChecked()) onView(withId(R.id.entrepreneursCB)).perform(click());
        if (cb_politics.isChecked()) onView(withId(R.id.politicsCB)).perform(click());
        if (cb_sports.isChecked()) onView(withId(R.id.sportsCB)).perform(click());
        if (cb_travel.isChecked()) onView(withId(R.id.travelCB)).perform(click());

        //We leave the notification switch unchecked
        if (notif_switch.isChecked()) onView(withId(R.id.notificationSwitch)).perform(click());


    }


    @After
    public void tearDown() throws Exception {

        mActivity = null;

    }

}