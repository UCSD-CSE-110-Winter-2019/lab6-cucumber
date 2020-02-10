package edu.ucsd.cse110.calculator.test.steps;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.nl.Gegeven;
import edu.ucsd.cse110.calculator.MainActivity;
import edu.ucsd.cse110.calculator.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.notNullValue;

public class SharedSteps {

    private ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity
            .class);

    private Map<String, String> nameIdMap = new HashMap<>();

    public SharedSteps() {
    }

    @Before
    public void setup() {
        Intents.init();
        nameIdMap.put("eerste", "number_1");
        nameIdMap.put("tweede", "number_2");
        nameIdMap.put("first", "number_1");
        nameIdMap.put("second", "number_2");
    }

    @After
    public void tearDown() {
        mActivityTestRule.getActivity().finish();
        Intents.release();
    }

    @Gegeven("een hoofdactiviteit")
    @Given("a main activity")
    public void aMainActivity() {
        System.out.println("STARTING MAINACTIVITY");
        mActivityTestRule.launchActivity(null);
        assertThat(mActivityTestRule.getActivity(), notNullValue());
    }

    public static int getLayoutIdFromString(String resName) {
        try {
            Field idField = R.id.class.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @When("^de gebruiker (\\d+) invoert in het (.*) tekstveld$")
//    @When("^the user enters (\\d+) in the (.*) text field$")
    public void theUserEntersANumberInTheEdittextFieldWithId(int number, String id) throws Throwable {
        int layoutId = getLayoutIdFromString(nameIdMap.get(id));
        onView(withId(layoutId))
                .check(matches(isDisplayed()))
                .perform(typeText("" + number));
    }

    @And("^de gebruiker klikt op de plus knop$")
    public void theUserClicksThePlusButton() throws Throwable {
        onView(withId(R.id.btn_plus))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    @Then("^is het antwoord 579$")
    public void theAnswerIs() throws Throwable {
        onView(withId(R.id.answer))
                .check(matches(isDisplayed()))
                .check(matches(withText("579")));
    }
}
