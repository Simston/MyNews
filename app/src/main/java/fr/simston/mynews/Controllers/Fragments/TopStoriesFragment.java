package fr.simston.mynews.Controllers.Fragments;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import fr.simston.mynews.Controllers.Activities.WebViewActivity;
import fr.simston.mynews.Controllers.Models.TopStoriesArticle.TopStoriesListArticles;
import fr.simston.mynews.Controllers.Utils.ItemClickSupport;
import fr.simston.mynews.Controllers.Utils.NewYorkTimesStreams;
import fr.simston.mynews.Controllers.Views.TopStoriesAdapter;
import fr.simston.mynews.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.fragment_topstories_recycler_view) RecyclerView mRecyclerView;

    // FOR DATA
    private Disposable mDisposable;

    // Declare Adapter
    private TopStoriesAdapter mAdapter;

    // Récupérer la position du ViewPager et afficher les informations
    public static BaseFragment newInstance(int position) {
        return new TopStoriesFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.top_stories_fragment;
    }

    @Override
    protected Disposable getDisposable() {return this.mDisposable;}

    @Override
    protected void callMethodsOnCreateView() {
        //Bundle
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
        this.mAdapter = new TopStoriesAdapter(Glide.with(this));
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.mRecyclerView.setAdapter(this.mAdapter);
        // 3.4 - Set layout manager to position the items
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    // --------------
    // HTTP (RxJava)
    // --------------
    // Execute the stream subscribing to Observable defined inside NewYorkTimesStream
    protected void executeHttpRequest(){

        this.mDisposable = NewYorkTimesStreams.streamFetchArticlesTopStories("home").subscribeWith(
                new DisposableObserver<TopStoriesListArticles>() {
                    @Override
                    public void onNext(TopStoriesListArticles results) {
                        Log.e("TAG", "On next");
                        updateUI(results);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "On error" + Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "On complete !!");
                    }
                });
    }

    // -------------------
    // UPDATE UI
    // -------------------
    private void updateUI(TopStoriesListArticles topStoriesListArticles){
        mAdapter.updateData(topStoriesListArticles.getResults());
    }

    // -----------------
    // ACTION
    // -----------------
    // 1 - Configure item click on RecyclerView
    private void configureOnClickRecyclerView(){

        ItemClickSupport.addTo(mRecyclerView, R.layout.top_stories_fragment_item)
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
