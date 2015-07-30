package am.narekb.alternativa.app;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import am.narekb.alternativa.R;

public class TabPagerAdapter extends FragmentPagerAdapter {

    CharSequence titles[];
    int numOfTabs;

    AppCompatActivity mCtx; //Must be passed down to StatsTab. getActivity() returns null, thus getWritableDatabase() doesn't work

    public TabPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNum) {
        super(fm);
        this.titles = mTitles;
        this.numOfTabs = mNum;
    }

    public void setContext (AppCompatActivity ctx) {
        mCtx = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        ScoreTab scoreTab = new ScoreTab();
        StatsTab statsTab = new StatsTab();

        statsTab.setContext(mCtx); //Passing down the Context from MainActivity
        scoreTab.setParent(this); //Needed for calling writeGameToDB() from ScoreTab
        if(position == 0) {
            return scoreTab;
        }
        else { //NOTE: All new tabs must use "else", and this one must be replaced with "if(position == 1)"
            return statsTab;
        }
    }

    @Override
    public int getItemPosition(Object object) {

        if (object instanceof StatsTab) {
            ((StatsTab) object).update();
        }
        return super.getItemPosition(object);
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
