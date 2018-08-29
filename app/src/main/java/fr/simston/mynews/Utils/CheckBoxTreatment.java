package fr.simston.mynews.Utils;

import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by St&eacute;phane Simon on 29/08/2018.
 *
 * @version 1.0
 */
public class CheckBoxTreatment {

    private CheckBox checkBoxArts, checkBoxPolitics, checkBoxBusiness,
                     checkBoxSport, checkBoxEntrepreneurs, checkBoxTravel;

    // -------------------
    // CHECKBOX TREATMENT
    // -------------------
    public String checkBoxTreatment() {
        ArrayList<String> optionsList = new ArrayList<String>();
        if (checkBoxArts.isChecked()) {
            optionsList.add("arts");
        }
        if (checkBoxPolitics.isChecked()) {
            optionsList.add("politics");
        }
        if (checkBoxBusiness.isChecked()) {
            optionsList.add("business");
        }
        if (checkBoxSport.isChecked()) {
            optionsList.add("sport");
        }
        if (checkBoxEntrepreneurs.isChecked()) {
            optionsList.add("entrepreneurs");
        }
        if (checkBoxTravel.isChecked()) {
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

    public void setCheckBoxArts(CheckBox checkBoxArts) {
        this.checkBoxArts = checkBoxArts;
    }

    public void setCheckBoxPolitics(CheckBox checkBoxPolitics) {
        this.checkBoxPolitics = checkBoxPolitics;
    }

    public void setCheckBoxBusiness(CheckBox checkBoxBusiness) {
        this.checkBoxBusiness = checkBoxBusiness;
    }

    public void setCheckBoxSport(CheckBox checkBoxSport) {
        this.checkBoxSport = checkBoxSport;
    }

    public void setCheckBoxEntrepreneurs(CheckBox checkBoxEntrepreneurs) {
        this.checkBoxEntrepreneurs = checkBoxEntrepreneurs;
    }

    public void setCheckBoxTravel(CheckBox checkBoxTravel) {
        this.checkBoxTravel = checkBoxTravel;
    }
}
