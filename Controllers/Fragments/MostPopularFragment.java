package fr.simston.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;

import fr.simston.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends BaseFragment {

    protected BaseFragment newInstance() {
        return new MostPopularFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.most_popular_fragment;
    }

    @Override
    protected void callMethodOnCreateView() {

    }
}
