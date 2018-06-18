package fr.simston.mynews.Controllers.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
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

    public TopStoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticle(TopStoriesArticles topStoriesArticle, RequestManager glide) {
        // Title of Article
        this.title.setText(topStoriesArticle.getTitle());
        this.section.setText(String.format("%s%s", topStoriesArticle.getSection(), ifSubsectionExist(topStoriesArticle)));

        // Update ImageView with Thumbnail
        List<Multimedium> multimediumList;
        multimediumList = topStoriesArticle.getMultimedia();
        updateImageView(multimediumList, glide);

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

    private void updateImageView(List<Multimedium> multimediumList, RequestManager glide) {
        if (multimediumList != null && !multimediumList.isEmpty()) {
            glide.load(multimediumList.get(0).getUrl()).apply(RequestOptions.centerInsideTransform()).into(this.mImageView);
        }

    }

}
