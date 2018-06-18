package fr.simston.mynews.Controllers.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.simston.mynews.Controllers.Models.TopStoriesArticle.TopStoriesArticles;
import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesViewHolder> {

    // FOR DATA
    private List<TopStoriesArticles> articles;

    // CONSTRUCTOR
    public TopStoriesAdapter() {
        this.articles = new ArrayList<>();
    }

    @Override
    public TopStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top_stories_fragment_item, parent, false);

        return new TopStoriesViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(TopStoriesViewHolder viewHolder, int position) {
        viewHolder.updateWithArticle(this.articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void updateData(List<TopStoriesArticles> list){
        this.articles = list;
        notifyDataSetChanged();
    }
}
