package fr.simston.mynews.Controllers.Fragments.SearchActivityFragments;


import android.support.v4.app.Fragment;

import fr.simston.mynews.Controllers.Fragments.BaseFragment;
import fr.simston.mynews.R;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        return new ResultFragment();
    }
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_result;
    }

    @Override
    protected void callMethodsOnCreateView() {
        modifToolbar();
    }

    @Override
    protected Disposable getDisposable() {
        return null;
    }

    @Override
    protected void executeHttpRequest() {

    }

    // -------------------------------
    // Modif Toolbar in ResultFragment
    // -------------------------------
    private void modifToolbar(){
//???????????
        //ActionBar ab = ((SearchActivity) getActivity()).getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);

    }
}
