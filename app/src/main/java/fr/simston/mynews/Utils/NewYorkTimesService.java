package fr.simston.mynews.Utils;

import fr.simston.mynews.Models.TopStoriesArticle.TopStoriesListArticles;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by St&eacute;phane Simon on 15/06/2018.
 *
 * @version 1.0
 */
public interface NewYorkTimesService {

    String api = "a2c0b5d9223d4c5eb1613ff5536b981f";
    String format = "json";

    @GET("{section}.{format}")
    Observable<TopStoriesListArticles> getTopStoriesArticles(@Path("section") String section, @Path("format") String format, @Query("apikey") String apikey);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
