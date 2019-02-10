package edu.ucsd.cse110.calculator.test.steps;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import edu.ucsd.cse110.calculator.MainActivity;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class MainActivitySteps {

    private ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity
            .class);

    public MainActivitySteps() {
    }

    @Before
    public void setup() {
        Intents.init();
        mActivityTestRule.launchActivity(null);
        assertThat(mActivityTestRule.getActivity(), notNullValue());
    }

    @After
    public void tearDown() {
        mActivityTestRule.getActivity().finish();
        Intents.release();
    }
}
