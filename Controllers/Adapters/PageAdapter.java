package fr.simston.mynews.Controllers.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.simston.mynews.Controllers.Fragments.ArtsFragment;
import fr.simston.mynews.Controllers.Fragments.MostPopularFragment;
import fr.simston.mynews.Controllers.Fragments.TopStoriesFragment;

/**
 * Created by St&eacute;phane Simon on 14/06/2018.
 *
 * @version 1.0
 */
public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3; // Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        // Page to return
        switch (position) {
            case 0:
                return new TopStoriesFragment();
            case 1:
                return new MostPopularFragment();
            case 2:
                return new ArtsFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Top Stories";
            case 1:
                return "Most Popular";
            case 2:
                return "Arts";
            default:
                return null;
        }
    }
}
