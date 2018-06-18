package fr.simston.mynews.Controllers.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.Controllers.Models.TopStoriesArticle.TopStoriesArticles;
import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class TopStoriesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.topstorie_item_title) TextView mTextView;

    public TopStoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticle(TopStoriesArticles topStoriesArticle){
        this.mTextView.setText(topStoriesArticle.getSection());
    }
}
