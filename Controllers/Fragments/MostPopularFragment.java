package fr.simston.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;

import fr.simston.mynews.R;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends BaseFragment {

    // FOR DATA
    public Disposable mDisposable;

    public static BaseFragment newInstance() {
        return new MostPopularFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.most_popular_fragment;
    }

    @Override
    protected void callMethodsOnCreateView() {

    }

    @Override
    protected Disposable getDisposable() {
        return this.mDisposable;
    }

    @Override
    protected void executeHttpRequest() {

    }
}
