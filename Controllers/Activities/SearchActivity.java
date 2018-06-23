package fr.simston.mynews.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.Controllers.Fragments.SearchActivityFragments.SearchFragment;
import fr.simston.mynews.R;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    private SearchFragment mSearchFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        configureAndShowSearchFragment();
        configureToolbar();

    }

    // -------
    // TOOLBAR
    // -------
    private void configureToolbar() {
        toolbar.setTitle("Search Articles");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void configureAndShowSearchFragment() {
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        mSearchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.searchFragment);
        if (mSearchFragment == null) {
            // B - Create new fragment
            mSearchFragment = new SearchFragment();

            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_search_fragment, mSearchFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
