package fr.simston.mynews.Controllers.Fragments.SearchActivityFragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Objects;

import butterknife.BindView;
import fr.simston.mynews.Controllers.Fragments.BaseFragment;
import fr.simston.mynews.Controllers.Models.SearchArticle.SearchArticles;
import fr.simston.mynews.Controllers.Utils.DateDialog;
import fr.simston.mynews.Controllers.Utils.NewYorkTimesService;
import fr.simston.mynews.Controllers.Utils.NewYorkTimesStreams;
import fr.simston.mynews.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment{

    @BindView(R.id.et_begin_date) AppCompatEditText et_begin_date;
    @BindView(R.id.et_end_date) AppCompatEditText et_end_date;
    @BindView(R.id.buttonSearch) Button btnSearch;
    String query = "foot";
    String sections = "sport";
    String benginDate = "20180620";
    String endDate = "20180623";
    LinkedHashMap<String, String> options = new LinkedHashMap<>();
    private ResultFragment mResultFragment;
    private Disposable mDisposable;


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
        return this.mDisposable;
    }

    @Override
    protected void executeHttpRequest() {
        options.put("begin_date", benginDate);
        options.put("end_date", endDate);
        options.put("facet_field", sections);
        options.put("api-key", NewYorkTimesService.api);


        this.mDisposable = NewYorkTimesStreams.streamFetchArticlesSearch(query,Collections.unmodifiableMap(options)).subscribeWith(
                new DisposableObserver<SearchArticles>() {
                    @Override
                    public void onNext(SearchArticles results) {
                        Log.e("TAG", "On next");
                        Log.e("TEST", String.valueOf(results.getResponse().getDocs().get(0).getHeadline().getMain()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "On error" + Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "On complete !!");
                    }
                });

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
                executeHttpRequest();
                configureAndShowResultFragment();
                Log.e("TAG", "lala");
            }
        });
    }


}
