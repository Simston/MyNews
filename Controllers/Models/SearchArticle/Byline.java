package fr.simston.mynews.Controllers.Models.SearchArticle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by St&eacute;phane Simon on 24/06/2018.
 *
 * @version 1.0
 */
public class Byline {

    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("person")
    @Expose
    private List<Person> person = null;
    @SerializedName("organization")
    @Expose
    private Object organization;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public Object getOrganization() {
        return organization;
    }

    public void setOrganization(Object organization) {
        this.organization = organization;
    }

}
