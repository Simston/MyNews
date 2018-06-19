package fr.simston.mynews.Controllers.Utils;

import java.util.concurrent.TimeUnit;

import fr.simston.mynews.Controllers.Models.MostPopularArticle.MostPopularListArticles;
import fr.simston.mynews.Controllers.Models.TopStoriesArticle.TopStoriesListArticles;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by St&eacute;phane Simon on 15/06/2018.
 *
 * @version 1.0
 */
public class NewYorkTimesStreams {

    public static Observable<TopStoriesListArticles> streamFetchArticlesTopStories(String section){
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getTopStoriesArticles(section, NewYorkTimesService.format, NewYorkTimesService.api)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<MostPopularListArticles> streamFetchArticlesMostViewed(){
        MostPopularService mostPopularService = MostPopularService.retrofit.create(MostPopularService.class);
        return mostPopularService.getMostPoularArticles("all-sections","1.json", NewYorkTimesService.api)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
