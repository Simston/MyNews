package fr.simston.mynews;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashMap;

import fr.simston.mynews.Models.MostPopularArticle.MostPopularListArticles;
import fr.simston.mynews.Models.SearchArticle.SearchArticles;
import fr.simston.mynews.Models.TopStoriesArticle.TopStoriesListArticles;
import fr.simston.mynews.Utils.NewYorkTimesService;
import fr.simston.mynews.Utils.NewYorkTimesStreams;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


//@RunWith(AndroidJUnit4.class)
public class ExampleUnitTest {

    /*  Override the default AndroidSchedulers.mainThread() Scheduler
        and return an instance which does not have these
        Android dependencies and can safely be instantiated */
    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }
    @Test
    public void streamFetchArticlesTopStoriesTest() {
        // 1 - Get the stream
        Observable<TopStoriesListArticles> observableTopStories = NewYorkTimesStreams.streamFetchArticlesTopStories("home");
        // 2 - Create new TestObserver
        TestObserver<TopStoriesListArticles> testObserver = new TestObserver<>();
        // 3 - Launch Observable
        observableTopStories.subscribeWith(testObserver)
                .assertNoErrors()       // 3.1 - Check if no errors
                .assertNoTimeout()      // 3.2 - Check if no Timeout
                .awaitTerminalEvent();  // 3.3 - Await the stream terminated before continue
        // 4 - Get list of user fetched
        TopStoriesListArticles articlesFetched = testObserver.values().get(0);
        // 5 - Verify if
        assertThat("The number of articles returned is ",articlesFetched.getResults().size() != 0);
    }

    @Test
    public void streamFetchArticlesMostPopularTest() {
        // 1 - Get the stream
        Observable<MostPopularListArticles> observableMostPopular = NewYorkTimesStreams.streamFetchArticlesMostViewed();
        // 2 - Create new TestObserver
        TestObserver<MostPopularListArticles> testObserver = new TestObserver<>();
        // 3 - Launch Observable
        observableMostPopular.subscribeWith(testObserver)
                .assertNoErrors()       // 3.1 - Check if no errors
                .assertNoTimeout()      // 3.2 - Check if no Timeout
                .awaitTerminalEvent();  // 3.3 - Await the stream terminated before continue
        // 4 - Get list of user fetched
        MostPopularListArticles articlesFetched = testObserver.values().get(0);
        // 5 - Verify if the result list articles returned is > 0
        assertThat("The number of articles returned is ",articlesFetched.getResults().size() > 0);
    }

    @Test
    public void streamFetchSearchArticleTest() {
        LinkedHashMap<String, String> options = new LinkedHashMap<>();
        options.put("q", "world");
        options.put("fq=news_desk", "politics+business");
        options.put("api-key", NewYorkTimesService.api);
        // 1 - Get the stream
        Observable<SearchArticles> observableSearchArticle = NewYorkTimesStreams.streamFetchArticlesSearch("world", Collections.unmodifiableMap(options));
        // 2 - Create new TestObserver
        TestObserver<SearchArticles> testObserver = new TestObserver<>();
        // 3 - Launch Observable
        observableSearchArticle.subscribeWith(testObserver)
                .assertNoErrors()       // 3.1 - Check if no errors
                .assertNoTimeout()      // 3.2 - Check if no Timeout
                .awaitTerminalEvent();  // 3.3 - Await the stream terminated before continue
        // 4 - Get list of user fetched
        SearchArticles articlesFetched = testObserver.values().get(0);
        // 5 - Verify if the result list articles returned is > 0
        assertThat("The number of articles returned is ",articlesFetched.getResponse().getDocs().size() > 0);
    }
}