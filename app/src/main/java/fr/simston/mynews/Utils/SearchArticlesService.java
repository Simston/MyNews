package fr.simston.mynews.Utils;

import java.util.Map;

import fr.simston.mynews.Models.SearchArticle.SearchArticles;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by St&eacute;phane Simon on 24/06/2018.
 *
 * @version 1.0
 */
public interface SearchArticlesService {

    @GET("articlesearch.json")
    Observable<SearchArticles> getSearchArticles(@Query("q") String query, @QueryMap Map<String, String> options);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/search/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

}
