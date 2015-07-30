package am.narekb.alternativa.app;

import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import am.narekb.alternativa.slidingtabs.SlidingTabLayout;


import am.narekb.alternativa.R;

public class MainActivity extends AppCompatActivity {

    CharSequence titles[] = {"Score", "History"};
    int numOfTabs = 2;
    public TabPagerAdapter adapter;
    ViewPager pager;
    SlidingTabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        //Replace ActionBar with Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Use getSupportActionBar.setTitle() for changing the Toolbar text

        adapter =  new TabPagerAdapter(getSupportFragmentManager(), titles, numOfTabs);
        adapter.setContext(this); //Replacement for getActivity(). Necessary for getWritableDatabase() in StatsTab

        tabs = (SlidingTabLayout) findViewById(R.id.slidingtabs);
        tabs.setDistributeEvenly(true);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        tabs.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
