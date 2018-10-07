package fr.simston.mynews.Views;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

    @BindView(R.id.topstorie_item_title)
    TextView title;
    @BindView(R.id.fragment_item_section)
    TextView section;
    @BindView(R.id.fragment_item_image)
    ImageView mImageView;
    @BindView(R.id.fragment_item_topstories_published_date)
    TextView publishedDate;

    private static final String TAG = ArticlesViewHolder.class.getSimpleName();

    public ArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        // ???
        /*if(this.mImageView == null){
            Glide.with(this.itemView)
                    .load(R.drawable.ny_logo)
                    .into(mImageView);
        }*/
    }

    public void updateWithArticle(T article, List<ArticleID> articleIDList) {

        if (article instanceof TopStoriesArticles) {
            // Title of Article
            this.title.setText(((TopStoriesArticles) article).getTitle());
            // Color of Title Text
            changeColorTextIfAlreadyVisited(articleIDList, ((TopStoriesArticles) article).getUrl());
            // Section an Subsection of Article
            this.section.setText(String.format("%s%s", ((TopStoriesArticles) article).getSection(), ifSubsectionExist(((TopStoriesArticles) article).getSubsection())));
            // Date format of Article
            this.publishedDate.setText(formatStringDate(((TopStoriesArticles) article).getPublishedDate()));
            // Update ImageView with Thumbnail
            List<Multimedium> multimediumList;
            multimediumList = ((TopStoriesArticles) article).getMultimedia();
            updateImageView(multimediumList);


        } else if (article instanceof MostPopularArticles) {
            // Title of Article
            this.title.setText(((MostPopularArticles) article).getTitle());
            //Color of TitleText
            changeColorTextIfAlreadyVisited(articleIDList, ((MostPopularArticles) article).getUrl());
            this.section.setText(((MostPopularArticles) article).getSection());
            this.publishedDate.setText(formatStringDate(((MostPopularArticles) article).getPublishedDate()));
            // Update ImageView with Thumbnail
            Glide.with(this.itemView.getContext())
                    .load(((MostPopularArticles) article).getMedia().get(0).getMediaMetadata().get(0).getUrl())
                    .apply(RequestOptions.centerCropTransform().error(R.drawable.ny_logo).placeholder(R.drawable.ny_logo))
                    .into(this.mImageView);
        } else if (article instanceof Docs) {
            this.title.setText(((Docs) article).getHeadline().getMain());
            this.section.setText(((Docs) article).getSectionName());
            try {
                this.publishedDate.setText(formatSearchDate(((Docs) article).getPubDate()));
                //Color of TitleText
                changeColorTextIfAlreadyVisited(articleIDList, ((Docs) article).getWebUrl());
            } catch (Exception e) {
                this.publishedDate.setText("");
            }
            try {
                Glide.with(this.itemView.getContext())
                        .load("https://nytimes.com/" + ((Docs) article).getMultimedia().get(0).getUrl())
                        .apply(RequestOptions.centerCropTransform().error(R.drawable.ny_logo).placeholder(R.drawable.ny_logo))
                        .into(this.mImageView);
            } catch (Exception ignored) {
                Glide.with(this.itemView.getContext())
                        .load(R.drawable.ny_logo)
                        .into(mImageView);
            }
        }
    }

    // ------------------------------------
    // CHANGE TEXT COLOR IF ALREADY VISITED
    // ------------------------------------
    private void changeColorTextIfAlreadyVisited(List<ArticleID> articleIDList, String url) {
        Log.e("URL","VIEWHOLDER " + url);
        Boolean iscontain = false;
        for (ArticleID element : articleIDList) {
            if (element.getUrlArticle().equals(url)) {
              iscontain = true;
            }
        }
        if (iscontain) {
            this.title.setTextColor(Color.rgb(10, 187, 210));
        }else{
            this.title.setTextColor(Color.rgb(0,0,0));
        }
    }

    // --------------------------------------------
    // CREATE A SPECIFIC STRING IF SUBSECTION EXIST
    // --------------------------------------------
    private String ifSubsectionExist(String section) {

        String subsection;
        if (!section.trim().isEmpty()) {
            subsection = " > " + section;
        } else {
            subsection = "";
        }
        return subsection;
    }

    private void updateImageView(List<Multimedium> multimediumList) {
        if (multimediumList != null && !multimediumList.isEmpty()) {
            Glide.with(this.itemView.getContext())
                    .load(multimediumList.get(0).getUrl())
                    .apply(RequestOptions.centerInsideTransform().error(R.drawable.ny_logo).placeholder(R.drawable.ny_logo))
                    .into(this.mImageView);
        }else{
            Glide.with(this.itemView.getContext())
                    .load(R.drawable.ny_logo)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(this.mImageView);
        }
    }

    private String formatSearchDate(String dateString) throws ParseException {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        @SuppressLint("SimpleDateFormat")
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

        for (int i = 8; i < 10; i++) {
            tabDay[i] = dateString.charAt(i);
            day = String.valueOf(tabDay);
        }
        for (int i = 5; i < 7; i++) {
            tabMonth[i] = dateString.charAt(i);
            month = String.valueOf(tabMonth);
        }
        for (int i = 2; i < 4; i++) {
            tabYear[i] = dateString.charAt(i);
            year = String.valueOf(tabYear);
        }
        return day + "/" + month + "/" + year;
    }
}
