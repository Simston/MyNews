package fr.simston.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by St&eacute;phane Simon on 14/06/2018.
 *
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment {

    // Force to implement those methods
    protected abstract BaseFragment newInstance();
    protected abstract int getFragmentLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get layout identifier from abstract method
        View view = inflater.inflate(getFragmentLayout(), container, false);

        // Configure design( Developer will call this method instead of override onCreateView())
        //this.configureDesign();
        return view;
    }

}
