package fr.simston.mynews.Controllers.Fragments.SearchActivityFragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

import butterknife.BindView;
import fr.simston.mynews.Controllers.Fragments.BaseFragment;
import fr.simston.mynews.Controllers.Utils.DateDialog;
import fr.simston.mynews.R;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment{

    @BindView(R.id.et_begin_date) AppCompatEditText et_begin_date;
    @BindView(R.id.et_end_date) AppCompatEditText et_end_date;
    @BindView(R.id.buttonSearch) Button btnSearch;
    private ResultFragment mResultFragment;


    public static BaseFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.search_fragment;
    }

    @Override
    protected void callMethodsOnCreateView() {
        setOnClickButtonSearch();
    }

    @Override
    protected Disposable getDisposable() {
        return null;
    }

    @Override
    protected void executeHttpRequest() {

    }

    /**
     * onStart
     * Initialisation des DatePicker
     */
    public void onStart() {
        super.onStart();
        etDatePickerConfig();
    }

    // -------
    // ACTION
    // -------
    private void etDatePickerConfig() {
        this.et_begin_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(v);
                android.app.FragmentTransaction ftz = Objects.requireNonNull(getActivity()).getFragmentManager().beginTransaction();
                dialog.show(ftz, "DatePicker");
            }
        });
        this.et_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(v);
                android.app.FragmentTransaction ftz = Objects.requireNonNull(getActivity()).getFragmentManager().beginTransaction();
                dialog.show(ftz, "DatePicker");
            }
        });
    }

    private void configureAndShowResultFragment() {
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        mResultFragment = (ResultFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_result_id);
        if (mResultFragment == null) {
            // B - Create new fragment
            mResultFragment = new ResultFragment();

            // C - Add it to FrameLayout container
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_search_fragment, mResultFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void setOnClickButtonSearch() {
        this.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configureAndShowResultFragment();
                Log.e("TAG", "lala");
            }
        });
    }
}
