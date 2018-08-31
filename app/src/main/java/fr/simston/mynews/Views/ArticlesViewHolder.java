package fr.simston.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.Models.MostPopularArticle.MostPopularArticles;
import fr.simston.mynews.Models.SearchArticle.Docs;
import fr.simston.mynews.Models.TopStoriesArticle.Multimedium;
import fr.simston.mynews.Models.TopStoriesArticle.TopStoriesArticles;
import fr.simston.mynews.Models.db.ArticleID;
import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class ArticlesViewHolder<T> extends RecyclerView.ViewHolder {

    @BindView(R.id.topstorie_item_title) TextView title;
    @BindView(R.id.fragment_item_section) TextView section;
    @BindView(R.id.fragment_item_image) ImageView mImageView;
    @BindView(R.id.fragment_item_topstories_published_date) TextView publishedDate;

    public ArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticle(T article) {

        String linkUrl;

        if(article instanceof TopStoriesArticles){
            // Title of Article
            this.title.setText(((TopStoriesArticles)article).getTitle());
            // Section an Subsection of Article
            this.section.setText(String.format("%s%s", ((TopStoriesArticles)article).getSection(), ifSubsectionExist(((TopStoriesArticles)article).getSubsection())));
            // Date format of Article
            this.publishedDate.setText(formatStringDate(((TopStoriesArticles)article).getPublishedDate()));
            // Save URL
            linkUrl = ((TopStoriesArticles)article).getUrl();
            saveArticleUrlInDB(linkUrl);
            // Update ImageView with Thumbnail
            List<Multimedium> multimediumList;
            multimediumList = ((TopStoriesArticles)article).getMultimedia();
            updateImageView(multimediumList);

        }
        else if(article instanceof MostPopularArticles){
            // Title of Article
            this.title.setText(((MostPopularArticles)article).getTitle());
            this.section.setText(((MostPopularArticles)article).getSection());
            this.publishedDate.setText(formatStringDate(((MostPopularArticles)article).getPublishedDate()));
            // Save URL
            linkUrl = ((MostPopularArticles)article).getUrl();
            saveArticleUrlInDB(linkUrl);
            // Update ImageView with Thumbnail
            Glide.with(this.itemView).load(((MostPopularArticles)article).getMedia().get(0).getMediaMetadata().get(0).getUrl()).apply(RequestOptions.centerCropTransform()).into(this.mImageView);
        }
        else if(article instanceof Docs){
            this.title.setText(((Docs)article).getHeadline().getMain());
            this.section.setText(((Docs)article).getSectionName());
            try{
                this.publishedDate.setText(formatSearchDate(((Docs)article).getPubDate()));
            }
            catch (Exception e){
                this.publishedDate.setText("");
            }
            // SAVE URL
            linkUrl = ((Docs)article).getWebUrl();
            saveArticleUrlInDB(linkUrl);
            try{
                Glide.with(this.itemView).load("https://nytimes.com/"+((Docs)article).getMultimedia().get(0).getUrl()).apply(RequestOptions.centerCropTransform()).into(this.mImageView);

            }catch (Exception ignored){

            }
        }
    }

    private String ifSubsectionExist(String section) {
        String subsection;
        Log.e("TAG", "subsection " + section);
        if (!section.trim().isEmpty()) {
            subsection = " > " + section;
        } else {
            subsection = "";
        }
        return subsection;
    }

    private void updateImageView(List<Multimedium> multimediumList) {
        if (multimediumList != null && !multimediumList.isEmpty()) {
            Glide.with(this.itemView).load(multimediumList.get(0).getUrl()).apply(RequestOptions.centerInsideTransform()).into(this.mImageView);
        }
    }
    // ---------------------------------------------
    // PERSISTENCE OF DATA FOR THE URL OF AN ARTICLE
    // ---------------------------------------------
    private void saveArticleUrlInDB(String url){
        ArticleID articleID = new ArticleID(url);
        // Verif in DB if exist or not and save it.
        if(!articleID.getIdUrl().equals(url)){
            articleID.save();
        }
    }

    private String formatSearchDate(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat simpleDateFormatFinal = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormatFinal.format(simpleDateFormat.parse(dateString));
    }

    private String formatStringDate(String dateString) {

        String day = null;
        String month = null;
        String year = null;

        int a = dateString.length();
        char[] tabDay = new char[a];
        char[] tabMonth = new char[a];
        char[] tabYear = new char[a];

        for(int i = 8; i < 10;i++){
            tabDay[i] = dateString.charAt(i);
            day = String.valueOf(tabDay);
        }
        for(int i = 5; i < 7;i++){
            tabMonth[i] = dateString.charAt(i);
            month = String.valueOf(tabMonth);
        }
        for(int i = 2; i < 4;i++){
            tabYear[i] = dateString.charAt(i);
            year = String.valueOf(tabYear);
        }
        return  day + "/" + month + "/"+ year;
    }
}
