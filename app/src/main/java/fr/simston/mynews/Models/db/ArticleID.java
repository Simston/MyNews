package fr.simston.mynews.Models.db;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by St&eacute;phane Simon on 31/08/2018.
 *
 * @version 1.0
 */
public class ArticleID extends SugarRecord {

    @Unique String idUrl;
    @Unique String alreadyVisited;

    // Default constructor is necessary for SugarRecord
    public ArticleID(){

    }

    public ArticleID(String idUrl, String alreadyVisited){
        this.idUrl = idUrl;
        this.alreadyVisited = alreadyVisited;
    }

    public String getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(String idUrl) {
        this.idUrl = idUrl;
    }

    public String getAlreadyVisited() {
        return alreadyVisited;
    }

    public void setAlreadyVisited(String alreadyVisited) {
        this.alreadyVisited = alreadyVisited;
    }
}
