package fr.simston.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;

import fr.simston.mynews.R;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */

public class ArtsFragment extends BaseFragment {

    // FOR DATA
    public Disposable mDisposable;

    public static BaseFragment newInstance() {
        return new ArtsFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.arts_fragment;
    }

    @Override
    protected void callMethodsOnCreateView() {
        executeHttpRequest();
    }

    @Override
    protected Disposable getDisposable() {
        return this.mDisposable;
    }

    @Override
    protected void executeHttpRequest() {

    }

}
