package fr.simston.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.simston.mynews.Controllers.Fragments.ArticlesFragment;
import fr.simston.mynews.R;

public class ResultActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY = "EXTRA_QUERY";
    public static final String EXTRA_BEGIN_DATE = "EXTRA_BEGIN_DATE";
    public static final String EXTRA_END_DATE = "EXTRA_END_DATE";
    public static final String EXTRA_CHECKBOX = "EXTRA_CHECKBOX";
    private static final String KEY_POSITION = "position";

    private String query, beginDate, endDate, checkBoxString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recoveryDataFromSearchActivity();
        configureAndShowArticleFragment();
    }

    // --------------
    // FRAGMENTS
    // --------------
    private void configureAndShowArticleFragment() {
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        ArticlesFragment articlesFragment = (ArticlesFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_result);
        if (articlesFragment == null) {
            // B - Create new main fragment
            articlesFragment = new ArticlesFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_POSITION, ArticlesFragment.CASE_RESULT_FRAGMENT);
            bundle.putString("query", query);
            bundle.putString("beginDate", beginDate);
            bundle.putString("endDate", endDate);
            bundle.putString("checkBoxString", checkBoxString);
            articlesFragment.setArguments(bundle);
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_result, articlesFragment)
                    .commit();
        }
    }

    private void recoveryDataFromSearchActivity() {
        Intent i = getIntent();
        query = i.getStringExtra(EXTRA_QUERY);
        beginDate = i.getStringExtra(EXTRA_BEGIN_DATE);
        endDate = i.getStringExtra(EXTRA_END_DATE);
        checkBoxString = i.getStringExtra(EXTRA_CHECKBOX);
    }
}
