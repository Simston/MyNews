package fr.simston.mynews;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.simston.mynews.Controllers.Activities.MainActivity;
import fr.simston.mynews.Controllers.Activities.SearchActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void checkIfTabsVisible() {
        onView(withText("Top Stories")).check(matches(isCompletelyDisplayed()));
        onView(withText("Most Popular")).check(matches(isCompletelyDisplayed()));
        onView(withText("Arts")).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void checkNavigationSearchArticle(){
        onView(withId(R.id.menu_action_search)).perform(click());
        intended(hasComponent(SearchActivity.class.getName()));
    }
}
