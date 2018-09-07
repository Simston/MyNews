package fr.simston.mynews.Models.MostPopularArticle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class MostPopularListArticles {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<MostPopularArticles> results = null;

    /**
     * No args constructor for use in serialization
     */
    public MostPopularListArticles() {
    }

    /**
     * @param results
     * @param status
     * @param numResults
     * @param copyright
     */
    public MostPopularListArticles(String status, String copyright, Integer numResults, List<MostPopularArticles> results) {
        super();
        this.status = status;
        this.copyright = copyright;
        this.numResults = numResults;
        this.results = results;
    }

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

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<MostPopularArticles> getResults() {
        return results;
    }

    public void setResults(List<MostPopularArticles> results) {
        this.results = results;
    }
}