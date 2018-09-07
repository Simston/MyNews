package fr.simston.mynews.Utils;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 31/05/2017.
 *
 * @version 1.0
 * <p>Création d'un DateDialog compatible</p>
 */
@SuppressLint("ValidFragment")
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private AppCompatEditText txtDate;

    private String dateForRequest;

    public DateDialog(View view) {
        txtDate = (AppCompatEditText) view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = day + "/" + (month + 1) + "/" + year;
        txtDate.setText(date);
        dateForRequest = String.valueOf(year + "" + monthAdd0(month + 1) + "" + day);
    }

    public void show(FragmentTransaction ftz, String datePicker) {

    }

    public String getDateForRequest() {
        return dateForRequest;
    }

    private String monthAdd0(int month) {
        if (month < 10) {
            return "0" + month;
        } else {
            return String.valueOf(month);
        }
    }
}
