package fr.simston.mynews.Controllers.Models.MostPopularArticle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class MostPopularArticles {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("adx_keywords")
    @Expose
    private String adxKeywords;
    @SerializedName("column")
    @Expose
    private Object column;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("type")
    @Expose
    private String type;
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
    @SerializedName("id")
    @Expose
    private Double id;
    @SerializedName("asset_id")
    @Expose
    private Double assetId;
    @SerializedName("views")
    @Expose
    private Double views;
    //@SerializedName("des_facet")
    //@Expose
    //private List<String> desFacet = null;
    //@SerializedName("org_facet")
    //@Expose
    //private List<String> orgFacet = null;
    //@SerializedName("per_facet")
    //@Expose
    //private List<String> perFacet = null;
    //@SerializedName("geo_facet")
    //@Expose
    //private List<String> geoFacet = null;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MostPopularArticles() {
    }

    /**
     *
     * @param adxKeywords
     * @param perFacet
     * @param orgFacet
     * @param geoFacet
     * @param desFacet
     * @param type
     * @param url
     * @param section
     * @param id
     * @param title
     * @param byline
     * @param source
     * @param assetId
     * @param views
     * @param column
     * @param _abstract
     * @param publishedDate
     * @param media
     */
    public MostPopularArticles(String url, String adxKeywords, Object column, String section, String byline, String type, String title, String _abstract, String publishedDate, String source, Double id, Double assetId, Double views, List<String> desFacet, List<String> orgFacet, List<String> perFacet, List<String> geoFacet, List<Medium> media) {
        super();
        this.url = url;
        this.adxKeywords = adxKeywords;
        this.column = column;
        this.section = section;
        this.byline = byline;
        this.type = type;
        this.title = title;
        this._abstract = _abstract;
        this.publishedDate = publishedDate;
        this.source = source;
        this.id = id;
        this.assetId = assetId;
        this.views = views;
        //this.desFacet = desFacet;
        //this.orgFacet = orgFacet;
        //this.perFacet = perFacet;
        //this.geoFacet = geoFacet;
        this.media = media;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    public Object getColumn() {
        return column;
    }

    public void setColumn(Object column) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getAssetId() {
        return assetId;
    }

    public void setAssetId(Double assetId) {
        this.assetId = assetId;
    }

    public Double getViews() {
        return views;
    }

    public void setViews(Double views) {
        this.views = views;
    }
    /*
    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<String> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<String> perFacet) {
        this.perFacet = perFacet;
    }

    /*public List<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }*/

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

}