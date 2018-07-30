package fr.simston.mynews.Models.SearchArticle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by St&eacute;phane Simon on 24/06/2018.
 *
 * @version 1.0
 */
public class SearchArticles {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private SearchListArticles response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public SearchListArticles getResponse() {
        return response;
    }

    public void setResponse(SearchListArticles response) {
        this.response = response;
    }

}
