package fr.simston.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.R;
import fr.simston.mynews.Utils.DateDialog;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.editTextSearchQuery) EditText mEditTextQuery;
    @BindView(R.id.et_begin_date) AppCompatEditText et_begin_date;
    @BindView(R.id.et_end_date) AppCompatEditText et_end_date;
    @BindView(R.id.buttonSearch) Button btnSearch;

    // Checkbox
    @BindView(R.id.checkBoxSearchArts) CheckBox mCheckBoxArts;
    @BindView(R.id.checkBoxSearchPolitics) CheckBox mCheckBoxPolitics;
    @BindView(R.id.checkBoxSearchBusiness) CheckBox mCheckBoxBusiness;
    @BindView(R.id.checkBoxSearchSport) CheckBox mCheckBoxSport;
    @BindView(R.id.checkBoxSearchEntrepreneurs) CheckBox mCheckBoxEntrepreneurs;
    @BindView(R.id.checkBoxSearchTravel) CheckBox mCheckBoxTravel;

    private String query = null;
    private String benginDate = null;
    private String endDate = null;
    private LinkedHashMap<String, String> options = new LinkedHashMap<>();

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
        this.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchActivity.this, ResultActivity.class);
                i.putExtra(ResultActivity.EXTRA_QUERY, mEditTextQuery.getText().toString());
                i.putExtra(ResultActivity.EXTRA_BEGIN_DATE, benginDate);
                i.putExtra(ResultActivity.EXTRA_END_DATE, endDate);
                i.putExtra(ResultActivity.EXTRA_CHECKBOX, checkBoxTreatment());
                startActivity(i);
               // executeHttpRequest();
            }
        });
    }

    private void getDataSearch(){
        query = mEditTextQuery.getText().toString();
        options.put("q",query);
        if(benginDate != null && !benginDate.equals("")){
            options.put("begin_date", benginDate);
        }
        if(endDate != null && !endDate.equals("")) {
            options.put("end_date", endDate);
        }
        options.put("fq=news_desk", checkBoxTreatment());
        Log.e("CheckBoxes", checkBoxTreatment());
    }

    // -------------------
    // CHECKBOX TRAITEMENT
    // -------------------
    private String checkBoxTreatment() {
        ArrayList<String> optionsList = new ArrayList<String>();
        if (mCheckBoxArts.isChecked()) {
            optionsList.add("arts");
        }
        if (mCheckBoxPolitics.isChecked()) {
            optionsList.add("politics");
        }
        if (mCheckBoxBusiness.isChecked()) {
            optionsList.add("business");
        }
        if (mCheckBoxSport.isChecked()) {
            optionsList.add("sport");
        }
        if (mCheckBoxEntrepreneurs.isChecked()) {
            optionsList.add("entrepreneurs");
        }
        if (mCheckBoxTravel.isChecked()) {
            optionsList.add("travel");
        }

        StringBuilder sb = new StringBuilder();
        for(int index=0; index < optionsList.size(); index++) {
            if(index == 0 && optionsList.size()-1 < 1) {
                // if opstionList.size() have one element only
                sb.append(optionsList.get(index));
            } else if(index == optionsList.size() - 1) {
                //the last element
                sb.append(optionsList.get(index));
            }else {
                sb.append(optionsList.get(index)).append("+");
            }
        }
        return String.valueOf(sb);
    }
}
