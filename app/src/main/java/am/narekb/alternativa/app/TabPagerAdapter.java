package am.narekb.alternativa.app;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    CharSequence titles[];
    int numOfTabs;

    public TabPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNum) {
        super(fm);
        this.titles = mTitles;
        this.numOfTabs = mNum;
    }

    @Override
    public Fragment getItem(int position) {
        ScoreTab scoreTab = new ScoreTab();
        StatsTab statsTab = new StatsTab();

        scoreTab.setStatsTab(statsTab);
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
