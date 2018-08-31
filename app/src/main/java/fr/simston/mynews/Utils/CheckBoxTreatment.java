package fr.simston.mynews.Utils;

import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by St&eacute;phane Simon on 29/08/2018.
 *
 * @version 1.0
 */
public class CheckBoxTreatment {

    public final static int TAG_CHECKBOX_ART = 10;
    public final static int TAG_CHECKBOX_POLITICS = 20;
    public final static int TAG_CHECKBOX_BUSINESS = 30;
    public final static int TAG_CHECKBOX_SPORT = 40;
    public final static int TAG_CHECKBOX_ENTREPRENEURS = 50;
    public final static int TAG_CHECKBOX_TRAVEL = 60;

    // -------------------
    // CHECKBOX TREATMENT
    // -------------------
    public void createStringWithCheckboxChecked(ArrayList<String> optionsList, CheckBox checkBox, int tag) {

        String param = null;
        switch(tag){
            case TAG_CHECKBOX_ART:
                if(checkBox.isChecked()) param = "arts";
                break;
            case TAG_CHECKBOX_POLITICS:
                if(checkBox.isChecked()) param = "politics";
                break;
            case TAG_CHECKBOX_BUSINESS:
                if(checkBox.isChecked()) param = "business";
                break;
            case TAG_CHECKBOX_SPORT:
                if(checkBox.isChecked()) param = "sport";
                break;
            case TAG_CHECKBOX_ENTREPRENEURS:
                if(checkBox.isChecked()) param = "entrepreneurs";
                break;
            case TAG_CHECKBOX_TRAVEL:
                if(checkBox.isChecked()) param = "travel";
                break;
        }
        optionsList.add(param);

    }

    public String convertArrayListToParam(ArrayList<String> optionsList){

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
    /**
     * Processing the string save in SharedPreferences and check the corresponding Checkboxes
     * @param checkBoxString String Saved with createStringWithCheckboxChecked()
     */
    public void stringCheckBoxTreatment(String checkBoxString, CheckBox checkBox, int tag){

        String[] separated = checkBoxString.split("\\+");

        for (String separateds: separated) {
            if(separateds.contains("arts") && tag == TAG_CHECKBOX_ART){
                checkBox.setChecked(true);
            }
            else if(separateds.contains("politics") && tag == TAG_CHECKBOX_POLITICS){
                checkBox.setChecked(true);
            }
            else if(separateds.contains("business") && tag == TAG_CHECKBOX_BUSINESS){
                checkBox.setChecked(true);
            }
            else if(separateds.contains("sport") && tag == TAG_CHECKBOX_SPORT){
                checkBox.setChecked(true);
            }
            else if(separateds.contains("entrepreneurs") && tag == TAG_CHECKBOX_ENTREPRENEURS){
                checkBox.setChecked(true);
            }
            else if(separateds.contains("travel") && tag == TAG_CHECKBOX_TRAVEL){
                checkBox.setChecked(true);
            }
        }
    }
}
