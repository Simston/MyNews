package fr.simston.mynews.Utils;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;


/**
 * Created by St&eacute;phane Simon on 06/07/2018.
 *
 * @version 1.0
 */
public class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onComplete() {
        Log.e("TAG", "On complete !!");
    }

    @Override
    public void onNext(T t) {
        Log.e("TAG", "On next");

    }

    @Override
    public void onError(Throwable exception) {
        Log.e("TAG", "On error" + Log.getStackTraceString(exception));
    }
}


