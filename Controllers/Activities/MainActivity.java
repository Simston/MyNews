package fr.simston.mynews.Controllers.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import fr.simston.mynews.Controllers.Adapters.PageAdapter;
import fr.simston.mynews.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureViewPagerAndTabs();
    }

    private void configureViewPagerAndTabs(){

        // Get ViewPager from layout
        ViewPager pager = (ViewPager)findViewById(R.id.activity_main_viewpager);

        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        // Get Tablayout from layout
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.activity_main_tabs);
        // Glue TabLayout and ViewPager together
        mTabLayout.setupWithViewPager(pager);
        // Deign purpose. Tabs have the same width
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }
}
