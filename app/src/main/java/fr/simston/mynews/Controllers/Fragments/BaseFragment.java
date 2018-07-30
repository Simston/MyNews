package fr.simston.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Created by St&eacute;phane Simon on 14/06/2018.
 *
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment {

    // Force to implement those methods
    protected abstract int getFragmentLayout();
    protected abstract void callMethodsOnCreateView();
    protected abstract Disposable getDisposable();
    protected abstract void executeHttpRequest();


    private Disposable mDisposable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get layout identifier from abstract method
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);

        callMethodsOnCreateView();
        this.mDisposable = getDisposable();
        // Configure design( Developer will call this method instead of override onCreateView())
        //this.configureDesign();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(this.mDisposable != null) this.disposeWhenDestroy();
    }
    private void disposeWhenDestroy() {
        if (!this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }


}
