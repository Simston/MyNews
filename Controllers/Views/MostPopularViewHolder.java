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
import fr.simston.mynews.Controllers.Models.TopStoriesArticle.TopStoriesArticles;
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

    public MostPopularViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticle(MostPopularArticles mostPopularArticles, RequestManager glide) {
        // Title of Article
        this.title.setText(mostPopularArticles.getTitle());
        this.section.setText(mostPopularArticles.getSection());

        // Update ImageView with Thumbnail
        glide.load(mostPopularArticles.getMedia().get(0).getMediaMetadata().get(0).getUrl()).apply(RequestOptions.centerCropTransform()).into(this.mImageView);


    }

    private String ifSubsectionExist(TopStoriesArticles topStoriesArticles) {
        String subsection;
        if (!topStoriesArticles.getSubsection().equals("")) {
            subsection = " > " + topStoriesArticles.getSubsection();
        } else {
            subsection = "";
        }
        return subsection;
    }
}
