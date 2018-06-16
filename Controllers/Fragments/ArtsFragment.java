package fr.simston.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;

import fr.simston.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class ArtsFragment extends BaseFragment {

    protected BaseFragment newInstance() {
        return new ArtsFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.arts_fragment;
    }

    @Override
    protected void callMethodOnCreateView() {

    }

}
