package fr.simston.mynews.Utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by St&eacute;phane Simon on 29/08/2018.
 *
 * @version 1.0
 */
public class JobCreatorCase implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        switch (tag) {
            case NotificationsUtils.TAG:
                return new NotificationsUtils();
            default:
                return null;
        }
    }
}
