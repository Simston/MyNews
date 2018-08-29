package fr.simston.mynews.Utils;

import android.app.Application;

import com.evernote.android.job.JobManager;

/**
 * Created by St&eacute;phane Simon on 29/08/2018.
 *
 * @version 1.0
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new JobCreatorCase());
    }
}
