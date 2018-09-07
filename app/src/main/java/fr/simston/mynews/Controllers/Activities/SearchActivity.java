package fr.simston.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.R;
import fr.simston.mynews.Utils.CheckBoxTreatment;
import fr.simston.mynews.Utils.DateDialog;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.editTextSearchQuery)
    EditText mEditTextQuery;
    @BindView(R.id.et_begin_date)
    AppCompatEditText et_begin_date;
    @BindView(R.id.et_end_date)
    AppCompatEditText et_end_date;
    @BindView(R.id.buttonSearch)
    Button btnSearch;

    // Checkbox
    @BindView(R.id.checkBoxSearchArts)
    CheckBox mCheckBoxArts;
    @BindView(R.id.checkBoxSearchPolitics)
    CheckBox mCheckBoxPolitics;
    @BindView(R.id.checkBoxSearchBusiness)
    CheckBox mCheckBoxBusiness;
    @BindView(R.id.checkBoxSearchSport)
    CheckBox mCheckBoxSport;
    @BindView(R.id.checkBoxSearchEntrepreneurs)
    CheckBox mCheckBoxEntrepreneurs;
    @BindView(R.id.checkBoxSearchTravel)
    CheckBox mCheckBoxTravel;

    private String benginDate = null;
    private String endDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        setOnClickButtonSearch();
    }

    /**
     * onStart
     * Initialisation des DatePicker
     */
    @Override
    protected void onStart() {
        super.onStart();
        etDatePickerConfig();
    }

    // -------
    // ACTION
    // -------
    private void etDatePickerConfig() {
        this.et_begin_date.setOnClickListener(view -> {
            DateDialog dialog = new DateDialog(view);
            dialog.show(getFragmentManager(), "DatePicker");
            benginDate = dialog.getDateForRequest();
        });

        this.et_end_date.setOnClickListener(view -> {
            DateDialog dialog = new DateDialog(view);
            dialog.show(getFragmentManager(), "DatePicker");
            endDate = dialog.getDateForRequest();
        });
    }

    private void setOnClickButtonSearch() {
        this.btnSearch.setOnClickListener(view -> {
            //force the user to fill the search field and check at least one category
            if (mEditTextQuery.getText().toString().equals("") && checkBoxTreatmentForButtonSearch().equals("")) {
                Toast.makeText(getApplicationContext(), "Please insert a search and check at least one category", Toast.LENGTH_SHORT).show();
            } else if (checkBoxTreatmentForButtonSearch().equals("")) {
                Toast.makeText(getApplicationContext(), "Please check at least one category", Toast.LENGTH_SHORT).show();
            } else if (mEditTextQuery.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Please insert a search", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(SearchActivity.this, ResultActivity.class);
                i.putExtra(ResultActivity.EXTRA_QUERY, mEditTextQuery.getText().toString());
                i.putExtra(ResultActivity.EXTRA_BEGIN_DATE, benginDate);
                i.putExtra(ResultActivity.EXTRA_END_DATE, endDate);
                i.putExtra(ResultActivity.EXTRA_CHECKBOX, checkBoxTreatmentForButtonSearch());
                startActivity(i);
            }
        });
    }

    private String checkBoxTreatmentForButtonSearch() {
        CheckBoxTreatment checkBoxTreatment = new CheckBoxTreatment();
        ArrayList<String> arrayList = new ArrayList<>();
        checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxArts, CheckBoxTreatment.TAG_CHECKBOX_ART);
        checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxPolitics, CheckBoxTreatment.TAG_CHECKBOX_POLITICS);
        checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxBusiness, CheckBoxTreatment.TAG_CHECKBOX_BUSINESS);
        checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxSport, CheckBoxTreatment.TAG_CHECKBOX_SPORT);
        checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxEntrepreneurs, CheckBoxTreatment.TAG_CHECKBOX_ENTREPRENEURS);
        checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxTravel, CheckBoxTreatment.TAG_CHECKBOX_TRAVEL);
        return checkBoxTreatment.convertArrayListToParam(arrayList);
    }
}
