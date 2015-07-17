package am.narekb.alternativa.app;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import am.narekb.alternativa.R;
import am.narekb.alternativa.db.DBHandler;

public class StatsTab extends Fragment {


    DBHandler dbHandler;
    ListView pastGames;

    public StatsTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.stats_tab, container, false);
        pastGames = (ListView) rootView.findViewById(R.id.pastGames);

        dbHandler = new DBHandler(getActivity());
        dbHandler.openDB();

        displayAllGames();
        return rootView;
    }

    public void displayAllGames() { //Populate ListView with all past Games from DB

        Cursor cursor = dbHandler.getAllGames();

        String[] fromFields = new String[]{DBHandler.KEY_ID, DBHandler.KEY_OUR_SCORE, DBHandler.KEY_THEIR_SCORE};
        int[] intoViews = new int[]{0, R.id.cardOurScore, R.id.cardTheirScore};


        SimpleCursorAdapter sca = new SimpleCursorAdapter(getActivity(), R.layout.item_layout, cursor, fromFields, intoViews, 0);
        pastGames.setAdapter(sca);
    }
}
