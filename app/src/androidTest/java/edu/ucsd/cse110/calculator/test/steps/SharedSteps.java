package edu.ucsd.cse110.calculator.test.steps;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ucsd.cse110.calculator.MainActivity;
import edu.ucsd.cse110.calculator.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
        nameIdMap.put("first", "number_1");
        nameIdMap.put("second", "number_2");
    }

    @After
    public void tearDown() {
        mActivityTestRule.getActivity().finish();
        Intents.release();
    }

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

    @When("^the user enters (\\d+) in the (.*) text field$")
    public void theUserEntersANumberInTheEdittextFieldWithId(int number, String id) throws Throwable {
        int layoutId = getLayoutIdFromString(nameIdMap.get(id));
        onView(withId(layoutId))
                .check(matches(isDisplayed()))
                .perform(typeText("" + number));
    }

    @And("^the user clicks the plus button$")
    public void theUserClicksThePlusButton() throws Throwable {
        onView(withId(R.id.btn_plus))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    @Then("^the answer is 579$")
    public void theAnswerIs() throws Throwable {
        onView(withId(R.id.answer))
                .check(matches(isDisplayed()))
                .check(matches(withText("579")));
    }
}
