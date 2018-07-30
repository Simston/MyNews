package fr.simston.mynews.Utils;

import fr.simston.mynews.Models.MostPopularArticle.MostPopularListArticles;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by St&eacute;phane Simon on 18/06/2018.
 *
 * @version 1.0
 */
public interface MostPopularService {
    @GET("{section}/{time-period}")
    Observable<MostPopularListArticles> getMostPoularArticles(@Path("section") String section, @Path("time-period") String timePeriod, @Query("apikey") String apikey);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/mostpopular/v2/mostviewed/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
