package am.narekb.alternativa.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.narekb.alternativa.R;

public class StatsTab extends Fragment {

    //TODO: Implement ListView with an SQLite DB adapter and save all past games


    public StatsTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.stats_tab, container, false);
    }


}
