package fr.simston.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.orm.SugarContext;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.simston.mynews.R;
import fr.simston.mynews.Views.PageAdapter;

public class MainActivity extends AppCompatActivity {

    // For Design
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_main_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.activity_main_viewpager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // init Context for SugarORM (save data sqlite)
        SugarContext.init(this);

        // Verification for Notification at start
        NotificationActivity notificationActivity = new NotificationActivity();
        notificationActivity.verificationNotificationIsAtivated(this);

        this.configureToolbar();
        this.configureViewPagerAndTabs();
    }

    // -----------------
    // TOOLBAR && MENU
    // -----------------
    private void configureToolbar() {
        // Set the Toolbar
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_action_search:
                Intent i = new Intent(this, SearchActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_main_params:
                // do something
                return true;
            case R.id.notification_menu:
                Intent notifIntent = new Intent(this, NotificationActivity.class);
                startActivity(notifIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // --------------------------------------
    // ViewPager And TabLayout configuration
    //---------------------------------------
    private void configureViewPagerAndTabs() {
        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        // Glue TabLayout and ViewPager together
        mTabLayout.setupWithViewPager(pager);
        // Deign purpose. Tabs have the same width
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }
}
