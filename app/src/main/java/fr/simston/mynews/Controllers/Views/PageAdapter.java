package fr.simston.mynews.Controllers.Views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.simston.mynews.Controllers.Fragments.ArticlesFragment;

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
        return ArticlesFragment.newInstance(position);
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
