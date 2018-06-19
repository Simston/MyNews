package fr.simston.mynews.Controllers.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import fr.simston.mynews.Controllers.Models.MostPopularArticle.MostPopularArticles;
import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 18/06/2018.
 *
 * @version 1.0
 */
public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularViewHolder> {
    // FOR DATA
    private List<MostPopularArticles> articles;
    private RequestManager glide;

    // CONSTRUCTOR
    public MostPopularAdapter(RequestManager glide) {
        this.articles = new ArrayList<>();
        this.glide = glide;
    }

    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.most_popular_fragment_item, parent, false);

        return new MostPopularViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(@NonNull MostPopularViewHolder viewHolder, int position) {
        viewHolder.updateWithArticle(this.articles.get(position),this.glide);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void updateData(List<MostPopularArticles> list){
        this.articles = list;
        notifyDataSetChanged();
    }
}
