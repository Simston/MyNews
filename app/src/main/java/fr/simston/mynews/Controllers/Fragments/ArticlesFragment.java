package fr.simston.mynews.Controllers.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.LinkedHashMap;

import butterknife.BindView;
import fr.simston.mynews.Controllers.Activities.SearchActivity;
import fr.simston.mynews.Controllers.Activities.WebViewActivity;
import fr.simston.mynews.Models.MostPopularArticle.MostPopularListArticles;
import fr.simston.mynews.Models.SearchArticle.SearchArticles;
import fr.simston.mynews.Models.TopStoriesArticle.TopStoriesListArticles;
import fr.simston.mynews.R;
import fr.simston.mynews.Utils.DefaultObserver;
import fr.simston.mynews.Utils.ItemClickSupport;
import fr.simston.mynews.Utils.NewYorkTimesService;
import fr.simston.mynews.Utils.NewYorkTimesStreams;
import fr.simston.mynews.Views.ArticlesAdapter;
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
    public static final int CASE_RESULT_FRAGMENT = 3;

    private int position;
    private String queryRecovery, beginDate, endDate, checkBoxString;
    private Boolean searchResultIsNotEmpty;

    // Declare Adapter
    private ArticlesAdapter mAdapter;

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
        this.queryRecovery = getArguments().getString("query");
        this.beginDate = getArguments().getString("beginDate");
        this.endDate = getArguments().getString("endDate");
        this.checkBoxString = getArguments().getString("checkBoxString");

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
            case CASE_RESULT_FRAGMENT : searchQueryArticles();
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

    private void searchQueryArticles(){
        LinkedHashMap<String, String> options = new LinkedHashMap<>();
        options.put("q",this.queryRecovery);
        if(this.beginDate != null && !this.beginDate.equals("")){
            options.put("begin_date", this.beginDate);
        }
        if(this.endDate != null && !this.endDate.equals("")) {
            options.put("end_date", this.endDate);
        }
        options.put("fq=news_desk", this.checkBoxString);
        options.put("api-key", NewYorkTimesService.api);

        this.mDisposable = NewYorkTimesStreams.streamFetchArticlesSearch(this.queryRecovery,Collections.unmodifiableMap(options)).subscribeWith(
                new DefaultObserver<SearchArticles>() {
                    @Override
                    public void onNext(SearchArticles results) {
                        Log.e("TAG", "On next");
                        if(results.getResponse().getDocs().isEmpty()){
                            Intent i = new Intent(getContext(), SearchActivity.class);
                            startActivity(i);
                            Toast.makeText(getContext(), "This search does not return any results", Toast.LENGTH_SHORT).show();
                        }else {
                            mAdapter.updateData(results.getResponse().getDocs());
                        }
                    }
                });
    }

    // -----------------
    // ACTION
    // -----------------
    // 1 - Configure item click on RecyclerView
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mRecyclerView, R.layout.articles_list_item)
                .setOnItemClickListener((recyclerView, position1, v) -> launchIntentWebView(position1));
    }

    private void launchIntentWebView(int position){
            Intent i = new Intent(getContext(), WebViewActivity.class);
            i.putExtra(WebViewActivity.EXTRA_URL, mAdapter.getUrlArticle(position) );
            startActivity(i);
    }
}
