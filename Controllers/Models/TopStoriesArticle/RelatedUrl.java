package fr.simston.mynews.Controllers.Models.TopStoriesArticle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by St&eacute;phane Simon on 18/06/2018.
 *
 * @version 1.0
 */
public class RelatedUrl {

    @SerializedName("suggested_link_text")
    @Expose
    private String suggestedLinkText;
    @SerializedName("url")
    @Expose
    private String url;

    public String getSuggestedLinkText() {
        return suggestedLinkText;
    }

    public void setSuggestedLinkText(String suggestedLinkText) {
        this.suggestedLinkText = suggestedLinkText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
