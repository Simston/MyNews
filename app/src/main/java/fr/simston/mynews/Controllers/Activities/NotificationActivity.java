package fr.simston.mynews.Controllers.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.evernote.android.job.JobManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.R;
import fr.simston.mynews.Utils.CheckBoxTreatment;
import fr.simston.mynews.Utils.JobCreatorCase;
import fr.simston.mynews.Utils.NewYorkTimesService;
import fr.simston.mynews.Utils.NotificationsUtils;

/**
 * Created by St&eacute;phane Simon on 30/07/2018.
 *
 * @version 1.0
 */
public class NotificationActivity extends AppCompatActivity{

    @BindView(R.id.toolbarNotif) Toolbar toolbar;

    // Checkbox
    @BindView(R.id.checkBoxNotifArts) CheckBox mCheckBoxArts;
    @BindView(R.id.checkBoxNotifPolitics) CheckBox mCheckBoxPolitics;
    @BindView(R.id.checkBoxNotifBusiness) CheckBox mCheckBoxBusiness;
    @BindView(R.id.checkBoxNotifSports) CheckBox mCheckBoxSport;
    @BindView(R.id.checkBoxNotifEntrepreneurs) CheckBox mCheckBoxEntrepreneurs;
    @BindView(R.id.checkBoxNotifTravel) CheckBox mCheckBoxTravel;

    // Search Field
    @BindView(R.id.editTextSearchQueryNotif) EditText mEditTextQuery;

    // Clear Button
    @BindView(R.id.clear_button_notification) Button mClearButton;

    // Shared preferences key
    private final String SHARED_PREF_NOTIF = "shared_prefs_notif";
    private final String QUERY_SEARCH = "query_search";
    private final String CHECKBOX_STRING = "checkbox_string";
    private final String SWITCH_ACTIVATED = "switch_activated";

    // For Data
    private SharedPreferences.Editor editor = null;
    private SharedPreferences mSharedPreferences;

    // Notification Button
    @BindView(R.id.switch1) Switch mSwitchButton;

    // For CheckBox treatments
    private CheckBoxTreatment checkBoxTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);

        // Initialize CheckBoxTreatment
        checkBoxTreatment = new CheckBoxTreatment();

        ButterKnife.bind(this);
        configureToolbar();
        restoreSharedPreferences();
        treatmentNotificationButton();
        clearButtonTreatment();

        searchQueryArticles();

    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreSharedPreferences();
    }

    /**
     * Call this method for verification of Notification preferences
     * @param context Get Context
     */
    public void verificationNotificationIsAtivated(Context context){
        String switch_activated;
        // Recover value of SwitchButton in SharedPreferences
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NOTIF, MODE_PRIVATE);
        switch_activated = mSharedPreferences.getString(SWITCH_ACTIVATED, "");
        if(switch_activated.equals("true")){
            JobManager.create(context).addJobCreator(new JobCreatorCase());
            NotificationsUtils.scheduleDaily();
        }
    }

    // -------
    // TOOLBAR
    // -------
    private void configureToolbar() {
        toolbar.setTitle("Notifications");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // -------------------
    // CHECKBOX TREATMENT
    // -------------------
    private String checkBoxVerification(){
        ArrayList<String> arrayList = new ArrayList<>();
        this.checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxArts, CheckBoxTreatment.TAG_CHECKBOX_ART);
        this.checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxPolitics, CheckBoxTreatment.TAG_CHECKBOX_POLITICS);
        this.checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxBusiness ,CheckBoxTreatment.TAG_CHECKBOX_BUSINESS);
        this.checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxSport, CheckBoxTreatment.TAG_CHECKBOX_SPORT);
        this.checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxEntrepreneurs, CheckBoxTreatment.TAG_CHECKBOX_ENTREPRENEURS);
        this.checkBoxTreatment.createStringWithCheckboxChecked(arrayList, mCheckBoxTravel, CheckBoxTreatment.TAG_CHECKBOX_TRAVEL);
        return this.checkBoxTreatment.convertArrayListToParam(arrayList);
    }

    // -----------------------
    // TREATMENT NOTIF BUTTON
    // -----------------------
    @SuppressLint("ResourceAsColor")
    private void treatmentNotificationButton(){
        mSwitchButton.setOnClickListener(view -> {
            editor = getSharedPreferences(SHARED_PREF_NOTIF, MODE_PRIVATE).edit();
            if(mSwitchButton.isChecked()){
                if(checkBoxVerification().equals("") && mEditTextQuery.getText().toString().equals("")){
                    mSwitchButton.setChecked(false);
                    Toast.makeText(this, "Please enter a search, and select at least one category", Toast.LENGTH_SHORT).show();
                }else if(checkBoxVerification().equals("")){
                    mSwitchButton.setChecked(false);
                    Toast.makeText(this, "Please select at least one category", Toast.LENGTH_SHORT).show();
                }else if(mEditTextQuery.getText().toString().equals("")){
                    mSwitchButton.setChecked(false);
                    Toast.makeText(this, "Please enter a search", Toast.LENGTH_SHORT).show();
                }else{
                    // Save Data User
                    editor.putString(QUERY_SEARCH,mEditTextQuery.getText().toString());
                    editor.putString(CHECKBOX_STRING, checkBoxVerification());
                    editor.putString(SWITCH_ACTIVATED, "true");
                    editor.apply();
                    // Call an AlertDialog and redirection to the MainActivity
                    alertDialogInterfaceOnClickSwitchButton();
                }
            }else{
                editor.clear().apply();
            }
        });
    }
    // -----------------------
    // TREATMENT CLEAR BUTTON
    // -----------------------
    private void clearButtonTreatment(){
        mClearButton.setOnClickListener(view ->{
            editor = getSharedPreferences(SHARED_PREF_NOTIF, MODE_PRIVATE).edit();
            // Remove all data in SharedPreferences
            editor.clear().apply();
            // Clear UI
            this.mEditTextQuery.setText("");
            this.mSwitchButton.setChecked(false);
            this.mCheckBoxArts.setChecked(false);
            this.mCheckBoxPolitics.setChecked(false);
            this.mCheckBoxBusiness.setChecked(false);
            this.mCheckBoxEntrepreneurs.setChecked(false);
            this.mCheckBoxSport.setChecked(false);
            this.mCheckBoxTravel.setChecked(false);
        });
    }
    // -------------
    // ALERT DIALOG
    // -------------
    private void alertDialogInterfaceOnClickSwitchButton(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Notification");
        alertDialog.setMessage("Your search is taken into account."+ "\n" +"Notifications for news articles will appear daily at noon");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", (dialog, which) -> {
            alertDialog.dismiss();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
        alertDialog.setOnShowListener(dialog ->
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary)));
        alertDialog.show();
    }
    // --------------------
    // RECOVERING DATA USER
    // --------------------
    private void restoreSharedPreferences(){

        // Recover all data
        this.mSharedPreferences = getSharedPreferences(SHARED_PREF_NOTIF, MODE_PRIVATE);
        String query = mSharedPreferences.getString(QUERY_SEARCH, "");
        String switchButton = mSharedPreferences.getString(SWITCH_ACTIVATED, "");
        String checkBoxVerif = mSharedPreferences.getString(CHECKBOX_STRING, "");

        // Set the data in UI
        this.mEditTextQuery.setText(query);
        if(switchButton.equals("true")){
            this.mSwitchButton.setChecked(true);
        }
        this.mEditTextQuery.setText(query);
        if(!checkBoxVerif.equals("")){
            this.checkBoxTreatment.stringCheckBoxTreatment(checkBoxVerif, mCheckBoxArts, CheckBoxTreatment.TAG_CHECKBOX_ART);
            this.checkBoxTreatment.stringCheckBoxTreatment(checkBoxVerif, mCheckBoxPolitics, CheckBoxTreatment.TAG_CHECKBOX_POLITICS);
            this.checkBoxTreatment.stringCheckBoxTreatment(checkBoxVerif, mCheckBoxBusiness, CheckBoxTreatment.TAG_CHECKBOX_BUSINESS);
            this.checkBoxTreatment.stringCheckBoxTreatment(checkBoxVerif, mCheckBoxEntrepreneurs, CheckBoxTreatment.TAG_CHECKBOX_ENTREPRENEURS);
            this.checkBoxTreatment.stringCheckBoxTreatment(checkBoxVerif, mCheckBoxSport, CheckBoxTreatment.TAG_CHECKBOX_SPORT);
            this.checkBoxTreatment.stringCheckBoxTreatment(checkBoxVerif, mCheckBoxTravel, CheckBoxTreatment.TAG_CHECKBOX_TRAVEL);
        }
    }

    private void searchQueryArticles(){

        Calendar calendar = Calendar.getInstance();
        String strDateNow;
        SimpleDateFormat simpleDateFormatFinal = new SimpleDateFormat("dd/MM/yyyy");
            strDateNow = simpleDateFormatFinal.format(calendar.getTime());
            Log.e("TAG", String.valueOf(strDateNow));

        // Date pour comparer avec le retour date des articles....


        LinkedHashMap<String, String> options = new LinkedHashMap<>();
        options.put("q",this.mEditTextQuery.getText().toString());
        //year+""+monthAdd0(month+1)+""+day
       // options.put("begin_date", this.beginDate);
        options.put("fq=news_desk", this.checkBoxVerification());
        options.put("api-key", NewYorkTimesService.api);

        /*
        this.mDisposable = NewYorkTimesStreams.streamFetchArticlesSearch(this.queryRecovery, Collections.unmodifiableMap(options)).subscribeWith(
                new DefaultObserver<SearchArticles>() {
                    @Override
                    public void onNext(SearchArticles results) {
                        Log.e("TAG", "On next");
                        if(results.getResponse().getDocs().isEmpty()){
                            Intent i = new Intent(getContext(), SearchActivity.class);
                            startActivity(i);
                            Toast.makeText(getContext(), "This search does not return any results", Toast.LENGTH_SHORT).show();
                        }else {
                            mAdapter.updateData(results.getResponse().getDocs());
                        }
                    }
                });*/
    }
}
