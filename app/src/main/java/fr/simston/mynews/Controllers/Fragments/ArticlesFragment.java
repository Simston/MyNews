package fr.simston.mynews.Controllers.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import fr.simston.mynews.Controllers.Activities.WebViewActivity;
import fr.simston.mynews.Controllers.Models.MostPopularArticle.MostPopularListArticles;
import fr.simston.mynews.Controllers.Models.TopStoriesArticle.TopStoriesListArticles;
import fr.simston.mynews.Controllers.Utils.DefaultObserver;
import fr.simston.mynews.Controllers.Utils.ItemClickSupport;
import fr.simston.mynews.Controllers.Utils.NewYorkTimesStreams;
import fr.simston.mynews.Controllers.Views.ArticlesAdapter;
import fr.simston.mynews.R;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.fragment_topstories_recycler_view) RecyclerView mRecyclerView;

    // FOR DATA
    private Disposable mDisposable;
    private static final String KEY_POSITION = "position";
    private int position;

    // Declare Adapter
    public static ArticlesAdapter mAdapter;

    // Récupérer la position du ViewPager et afficher les informations
    public static ArticlesFragment newInstance(int position) {

        ArticlesFragment frag = new ArticlesFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.articles_fragment;
    }

    @Override
    protected Disposable getDisposable() {return this.mDisposable;}

    @Override
    protected void callMethodsOnCreateView() {
        //Bundle
        this.position = getArguments().getInt(KEY_POSITION, -1);

        configureRecyclerView();
        configureOnClickRecyclerView();
        executeHttpRequest();
    }

    // -----------------
    // CONFIGURATION
    // -----------------
    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 3.2 - Create adapter passing the list of users
        mAdapter = new ArticlesAdapter(Glide.with(this));
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.mRecyclerView.setAdapter(mAdapter);
        // 3.4 - Set layout manager to position the items
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    // --------------
    // HTTP (RxJava)
    // --------------
    // Execute the stream subscribing to Observable defined inside NewYorkTimesStream
    protected void executeHttpRequest(){
        switch (position){
            case 0: topStoriesArticlesHome();
            break;
            case 1: mostPopularArticles();
            break;
            case 2: topStoriesArticlesArts();
            break;
        }

    }

    private void topStoriesArticlesHome(){
        this.mDisposable = NewYorkTimesStreams.streamFetchArticlesTopStories("home")
                .subscribeWith(new DefaultObserver<TopStoriesListArticles>(){
                    @Override
                    public void onNext(TopStoriesListArticles topStoriesListArticles) {
                        super.onNext(topStoriesListArticles);
                        mAdapter.updateData(topStoriesListArticles.getResults());
                    }
                });
    }

    private void mostPopularArticles(){
        this.mDisposable = NewYorkTimesStreams.streamFetchArticlesMostViewed()
                .subscribeWith(new DefaultObserver<MostPopularListArticles>(){
                    @Override
                    public void onNext(MostPopularListArticles mostPopularListArticles) {
                        super.onNext(mostPopularListArticles);
                        mAdapter.updateData(mostPopularListArticles.getResults());
                    }
                });
    }

    private void topStoriesArticlesArts(){
        this.mDisposable = NewYorkTimesStreams.streamFetchArticlesTopStories("arts")
                .subscribeWith(
                new DefaultObserver<TopStoriesListArticles>(){
                    @Override
                    public void onNext(TopStoriesListArticles topStoriesListArticlesArts) {
                        super.onNext(topStoriesListArticlesArts);
                        mAdapter.updateData(topStoriesListArticlesArts.getResults());

                    }
                });
    }

    // -----------------
    // ACTION
    // -----------------
    // 1 - Configure item click on RecyclerView
    private void configureOnClickRecyclerView(){

        ItemClickSupport.addTo(mRecyclerView, R.layout.articles_list_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        Intent i = new Intent(getContext(), WebViewActivity.class);
                        i.putExtra(WebViewActivity.EXTRA_URL, mAdapter.getUrlArticle(position) );
                        startActivity(i);
                    }
                });
    }
}