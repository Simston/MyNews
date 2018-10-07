package fr.simston.mynews.Models.db;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by St&eacute;phane Simon on 31/08/2018.
 *
 * @version 1.0
 */
public class ArticleID extends SugarRecord {

    @Unique
    String urlArticle;

    // Default constructor is necessary for SugarRecord
    public ArticleID() {

    }

    public ArticleID(String urlArticle) {
        this.urlArticle = urlArticle;
    }

    public String getUrlArticle() {
        return urlArticle;
    }

    public void setUrlArticle(String urlArticle) {
        this.urlArticle = urlArticle;
    }

}
