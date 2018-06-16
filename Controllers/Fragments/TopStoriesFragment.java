package fr.simston.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import fr.simston.mynews.Controllers.Models.TopStoriesArticles;
import fr.simston.mynews.Controllers.Models.TopStoriesListArticles;
import fr.simston.mynews.Controllers.Utils.NewYorkTimesStreams;
import fr.simston.mynews.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.textViewTopStories)
    TextView mTextView;

    protected BaseFragment newInstance() {
        return new TopStoriesFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.top_stories_fragment;
    }

    @Override
    protected void callMethodOnCreateView() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // --------------
    // HTTP (RxJava)
    // --------------

    // Execute the stream subscribing to Observable defined inside NewYorkTimesStream
    private Disposable mDisposable = NewYorkTimesStreams.streamFetchArticlesTopStories("home").subscribeWith(
            new DisposableObserver<TopStoriesListArticles>(){

                @Override
                public void onNext(TopStoriesListArticles articlesTopStories) {
                    Log.e("TAG", "On next");
                    updateUiWithTopStoriesArticles(articlesTopStories);
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

    private void disposeWhenDestroy(){
        if(!this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    private void updateUiWithTopStoriesArticles(TopStoriesListArticles topStoriesListArticles){
        StringBuilder stringBuilder = new StringBuilder();
        for(TopStoriesArticles.Result topStorieArticle : topStoriesListArticles.getResults()){
            stringBuilder.append("-").append(topStorieArticle.getTitle()).append("\n");
        }
        mTextView.setText(stringBuilder.toString());
    }
}
