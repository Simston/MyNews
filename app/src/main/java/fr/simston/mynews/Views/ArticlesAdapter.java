package fr.simston.mynews.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import fr.simston.mynews.Models.MostPopularArticle.MostPopularArticles;
import fr.simston.mynews.Models.SearchArticle.Docs;
import fr.simston.mynews.Models.TopStoriesArticle.TopStoriesArticles;
import fr.simston.mynews.Models.db.ArticleID;
import fr.simston.mynews.R;

/**
 * Created by St&eacute;phane Simon on 16/06/2018.
 *
 * @version 1.0
 */
public class ArticlesAdapter<T> extends RecyclerView.Adapter<ArticlesViewHolder> {

    // FOR DATA
    private List<T> articles;
    private RequestManager glide;
    private List<ArticleID> articleIDList;

    // CONSTRUCTOR
    public ArticlesAdapter(RequestManager glide, List<ArticleID> articleIDList) {
        this.articleIDList = articleIDList;
        this.articles = new ArrayList<>();
        this.glide = glide;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.articles_list_item, parent, false);
        return new ArticlesViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A ARTICLES
    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder viewHolder, int position) {
        viewHolder.updateWithArticle(this.articles.get(position), this.articleIDList);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void updateData(List<T> list){
        this.articles = list;
        notifyDataSetChanged();
    }

    public String getUrlArticle(int position){
        String urlArticle = null;
        T article = articles.get(position);
        if(article instanceof TopStoriesArticles){
            urlArticle = ((TopStoriesArticles)article).getUrl();
        }
        else if(article instanceof MostPopularArticles){
            urlArticle = ((MostPopularArticles)article).getUrl();
        }else if (article instanceof Docs){
            urlArticle = ((Docs)article).getWebUrl();
            Log.e("URL",urlArticle);
        }
        return urlArticle;
    }
}
