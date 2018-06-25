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

public class ArtsFragment extends BaseFragment {
    // FOR DESIGN
    @BindView(R.id.fragment_arts_recycler_view)
    RecyclerView mRecyclerView;

    // Declare Adapter
    private TopStoriesAdapter mAdapter;

    // FOR DATA
    public Disposable mDisposable;

    public static BaseFragment newInstance() {
        return new ArtsFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.arts_fragment;
    }

    @Override
    protected void callMethodsOnCreateView() {
        executeHttpRequest();
        configureRecyclerView();
        configureOnClickRecyclerView();
    }

    @Override
    protected Disposable getDisposable() {
        return this.mDisposable;
    }

    // -----------------
    // CONFIGURATION
    // -----------------
    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 3.2 - Create adapter passing the list of users
        this.mAdapter = new TopStoriesAdapter();
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.mRecyclerView.setAdapter(this.mAdapter);
        // 3.4 - Set layout manager to position the items
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    // --------------
    // HTTP (RxJava)
    // --------------
    // Execute the stream subscribing to Observable defined inside NewYorkTimesStream
    @Override
    protected void executeHttpRequest(){

        this.mDisposable = NewYorkTimesStreams.streamFetchArticlesTopStories("arts").subscribeWith(
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
                        i.putExtra("url", mAdapter.getUrlArticle(position) );
                        startActivity(i);
                    }
                });
    }

}
