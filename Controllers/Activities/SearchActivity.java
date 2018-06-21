package fr.simston.mynews.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.Controllers.Utils.DateDialog;
import fr.simston.mynews.R;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.et_begin_date)
    AppCompatEditText et_begin_date;
    @BindView(R.id.et_end_date) AppCompatEditText et_end_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        configureToolbar();

    }

    /**
     * onStart
     * Initialisation des DatePicker
     */
    public void onStart() {
        super.onStart();
        etDatePickerConfig();
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

    // -------
    // ACTION
    // -------
    private void etDatePickerConfig(){
        et_begin_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(v);
                android.app.FragmentTransaction ftz = getFragmentManager().beginTransaction();
                dialog.show(ftz, "DatePicker");
            }
        });
        et_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(v);
                android.app.FragmentTransaction ftz = getFragmentManager().beginTransaction();
                dialog.show(ftz, "DatePicker");
            }
        });
    }
}
