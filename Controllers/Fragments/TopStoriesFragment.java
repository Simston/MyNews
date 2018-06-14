package fr.simston.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;

import fr.simston.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends BaseFragment {

    protected BaseFragment newInstance() {
        return new TopStoriesFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.top_stories_fragment;
    }
}
