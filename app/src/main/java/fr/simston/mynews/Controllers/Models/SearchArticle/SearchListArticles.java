package fr.simston.mynews.Controllers.Models.SearchArticle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by St&eacute;phane Simon on 24/06/2018.
 *
 * @version 1.0
 */
public class SearchListArticles {

        @SerializedName("docs")
        @Expose
        private List<Docs> docs = null;

        public List<Docs> getDocs() {
            return docs;
        }

        public void setDocs(List<Docs> docs) {
            this.docs = docs;
        }

}
