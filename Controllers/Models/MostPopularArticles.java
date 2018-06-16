package fr.simston.mynews.Controllers.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class MostPopularArticles {

    public class DesFacet {


    }

    public class GeoFacet {


    }


    public class OrgFacet {


    }

    public class PerFacet {


    }

    public class Result {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("column")
        @Expose
        private String column;
        @SerializedName("section")
        @Expose
        private String section;
        @SerializedName("byline")
        @Expose
        private String byline;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("abstract")
        @Expose
        private String _abstract;
        @SerializedName("published_date")
        @Expose
        private String publishedDate;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("des_facet")
        @Expose
        private DesFacet desFacet;
        @SerializedName("org_facet")
        @Expose
        private OrgFacet orgFacet;
        @SerializedName("per_facet")
        @Expose
        private PerFacet perFacet;
        @SerializedName("geo_facet")
        @Expose
        private GeoFacet geoFacet;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public String getByline() {
            return byline;
        }

        public void setByline(String byline) {
            this.byline = byline;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAbstract() {
            return _abstract;
        }

        public void setAbstract(String _abstract) {
            this._abstract = _abstract;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public DesFacet getDesFacet() {
            return desFacet;
        }

        public void setDesFacet(DesFacet desFacet) {
            this.desFacet = desFacet;
        }

        public OrgFacet getOrgFacet() {
            return orgFacet;
        }

        public void setOrgFacet(OrgFacet orgFacet) {
            this.orgFacet = orgFacet;
        }

        public PerFacet getPerFacet() {
            return perFacet;
        }

        public void setPerFacet(PerFacet perFacet) {
            this.perFacet = perFacet;
        }

        public GeoFacet getGeoFacet() {
            return geoFacet;
        }

        public void setGeoFacet(GeoFacet geoFacet) {
            this.geoFacet = geoFacet;
        }

    }
}