package fr.simston.mynews.Controllers.Models.TopStoriesArticle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class TopStoriesListArticles {
    @SerializedName("results")
    @Expose
    private List<TopStoriesArticles> results = null;

    public List<TopStoriesArticles> getResults() {
        return results;
    }

    public void setResults(List<TopStoriesArticles> results) {
        this.results = results;
}
}
