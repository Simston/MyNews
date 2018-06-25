package fr.simston.mynews.Controllers.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.Controllers.Models.MostPopularArticle.MostPopularArticles;
import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 18/06/2018.
 *
 * @version 1.0
 */
public class MostPopularViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.mostpopular_item_title)
    TextView title;
    @BindView(R.id.fragment_mostpopular_item_section)
    TextView section;
    @BindView(R.id.fragment_mostpopular_item_image)
    ImageView mImageView;
    @BindView(R.id.fragment_item_mostpopular_published_date) TextView publishedDate;

    public MostPopularViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticle(MostPopularArticles mostPopularArticles, RequestManager glide) {
        // Title of Article
        this.title.setText(mostPopularArticles.getTitle());
        this.section.setText(mostPopularArticles.getSection());
        this.publishedDate.setText(formatStringDate(mostPopularArticles.getPublishedDate()));

        // Update ImageView with Thumbnail
        glide.load(mostPopularArticles.getMedia().get(0).getMediaMetadata().get(0).getUrl()).apply(RequestOptions.centerCropTransform()).into(this.mImageView);


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
