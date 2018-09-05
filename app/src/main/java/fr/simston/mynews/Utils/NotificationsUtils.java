package fr.simston.mynews.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.evernote.android.job.DailyJob;
import com.evernote.android.job.JobRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import fr.simston.mynews.Controllers.Activities.MainActivity;
import fr.simston.mynews.Controllers.Activities.NotificationActivity;
import fr.simston.mynews.Models.SearchArticle.SearchArticles;
import fr.simston.mynews.R;
import io.reactivex.disposables.CompositeDisposable;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by St&eacute;phane Simon on 30/07/2018.
 *
 * @version 1.0
 */
public final class NotificationsUtils extends DailyJob {

    private static final int NOTIFICATION_ID = 007;
    private static final String NOTIFICATION_TAG = "FIREBASEOC";

    public static final String TAG = "show_notification_job_tag";

    private CompositeDisposable disposable = new CompositeDisposable();

    @NonNull
    @Override
    protected DailyJobResult onRunDailyJob(@NonNull Params params) {

        searchQueryArticles();

        return DailyJobResult.SUCCESS;
    }

    public static void scheduleDaily(){
        DailyJob.schedule(new JobRequest.Builder(NotificationsUtils.TAG),
                TimeUnit.HOURS.toMillis(12), TimeUnit.HOURS.toMillis(12)+TimeUnit.MINUTES.toMillis(15));
    }

    // For Jobs every 15minutes (minimum)
    public static void schedulePeriodic() {
                new JobRequest.Builder(NotificationsUtils.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setUpdateCurrent(true)
                //.setPersisted(true)
                .build()
                .schedule();
    }

    // FOR TESTING
    public static void runJobImmediately() {
        new JobRequest.Builder(NotificationsUtils.TAG)
                .startNow()
                .build()
                .schedule();
    }

    private void searchQueryArticles(){

        Calendar calendar = Calendar.getInstance();
        String strDateNow;
        SimpleDateFormat simpleDateFormatFinal = new SimpleDateFormat("yyyyMMdd");
        Log.e("TEST DATE", String.valueOf(simpleDateFormatFinal));
        //year+""+monthAdd0(month+1)+""+day
        strDateNow = simpleDateFormatFinal.format(calendar.getTime());
        Log.e("TAG", String.valueOf(strDateNow));

        // Recover all data
        SharedPreferences mSharedPreferences = getContext().getSharedPreferences(NotificationActivity.SHARED_PREF_NOTIF, MODE_PRIVATE);
        String query = mSharedPreferences.getString(NotificationActivity.QUERY_SEARCH, "");
        String checkBoxVerif = mSharedPreferences.getString(NotificationActivity.CHECKBOX_STRING, "");


        LinkedHashMap<String, String> options = new LinkedHashMap<>();
        options.put("q", query);
        options.put("fq=news_desk", checkBoxVerif);
        options.put("api-key", NewYorkTimesService.api);

        this.disposable.add(NewYorkTimesStreams.streamFetchArticlesSearch(query, Collections.unmodifiableMap(options)).subscribeWith(
                new DefaultObserver<SearchArticles>() {
                    @Override
                    public void onNext(SearchArticles results) {
                        Log.e("TAG", "On next");
                            showNotification(results.getResponse().getDocs().size());
                    }
                }));
    }

    private void showNotification(int numberArticles){
        // 1 - Create an Intent that will be shown when user will click on the Notification
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // 2 - Create a Style for the Notification
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("New articles available");
        inboxStyle.addLine("There is "+ numberArticles + " article available");

        // 3 - Create a Channel (Android 8)
        String channelId = "Articles channel";

        // 4 - Build a Notification object
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getContext(), channelId)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(getContext().getString(R.string.app_name))
                        .setContentText("New articles available")
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentIntent(pendingIntent)
                        .setStyle(inboxStyle);

        // 5 - Add the Notification to the Notification Manager and show it.
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        // 6 - Support Version >= Android 8
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Articles channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        // 7 - Show notification
        notificationManager.notify(NOTIFICATION_TAG, NOTIFICATION_ID, notificationBuilder.build());
    }
}
