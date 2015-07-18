package am.narekb.alternativa.app;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    CharSequence titles[];
    int numOfTabs;

    Context mCtx; //Must be passed down to StatsTab. getActivity() returns null, thus getWritableDatabase() doesn't work

    public TabPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNum) {
        super(fm);
        this.titles = mTitles;
        this.numOfTabs = mNum;
    }

    public void setContext (Context ctx) {
        mCtx = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        ScoreTab scoreTab = new ScoreTab();
        StatsTab statsTab = new StatsTab();

        statsTab.setContext(mCtx); //Passing down the Context from MainActivity
        scoreTab.setStatsTab(statsTab); //Needed for calling writeGameToDB() from ScoreTab
        if(position == 0) {
            return scoreTab;
        }
        else { //NOTE: All new tabs must use "else", and this one must be replaced with "if(position == 1)"
            return statsTab;
        }
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }


}
