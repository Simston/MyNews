package fr.simston.mynews.Controllers.Views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.Controllers.Models.TopStoriesArticle.Multimedium;
import fr.simston.mynews.Controllers.Models.TopStoriesArticle.TopStoriesArticles;
import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class TopStoriesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.topstorie_item_title)
    TextView title;
    @BindView(R.id.fragment_item_section)
    TextView section;
    @BindView(R.id.fragment_item_image)
    ImageView mImageView;
    @BindView(R.id.fragment_item_topstories_published_date)
    TextView publishedDate;

    public TopStoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticle(TopStoriesArticles topStoriesArticle) {
        // Title of Article
        this.title.setText(topStoriesArticle.getTitle());
        // Section an Subsection of Article
        this.section.setText(String.format("%s%s", topStoriesArticle.getSection(), ifSubsectionExist(topStoriesArticle)));
        // Date format of Article
        this.publishedDate.setText(formatStringDate(topStoriesArticle.getPublishedDate()));
        // Update ImageView with Thumbnail
        List<Multimedium> multimediumList;
        multimediumList = topStoriesArticle.getMultimedia();
        updateImageView(multimediumList);
    }

    private String ifSubsectionExist(TopStoriesArticles topStoriesArticles) {
        String subsection;
        Log.e("TAG", "subsection " + topStoriesArticles.getSubsection());
        if (!topStoriesArticles.getSubsection().trim().isEmpty()) {
            subsection = " > " + topStoriesArticles.getSubsection();
        } else {
            subsection = "";
        }
        return subsection;
    }

    private void updateImageView(List<Multimedium> multimediumList) {
        if (multimediumList != null && !multimediumList.isEmpty()) {
            Glide.with(this.mImageView).load(multimediumList.get(0).getUrl()).apply(RequestOptions.centerInsideTransform()).into(this.mImageView);
        }
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
