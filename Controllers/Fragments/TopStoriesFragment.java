package fr.simston.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import fr.simston.mynews.Controllers.Models.TopStoriesArticle.TopStoriesListArticles;
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
    @BindView(R.id.fragment_topstories_recycler_view)
    RecyclerView mRecyclerView;

    // Declare list of TopStoriesArticles & Adapter

    private TopStoriesAdapter mAdapter;

    public static BaseFragment newInstance() {
        return new TopStoriesFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.top_stories_fragment;
    }

    @Override
    protected void callMethodOnCreateView() {
        configureRecyclerView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
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
    private Disposable mDisposable = NewYorkTimesStreams.streamFetchArticlesTopStories("home").subscribeWith(
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

    private void disposeWhenDestroy() {
        if (!this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------


    private void updateUI(TopStoriesListArticles topStoriesListArticles){
        mAdapter.updateData(topStoriesListArticles.getResults());
    }
}
