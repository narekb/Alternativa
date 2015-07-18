package am.narekb.alternativa.app;


import android.content.Context;
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
import am.narekb.alternativa.db.Game;

public class StatsTab extends Fragment {

    DBHandler dbHandler;
    ListView pastGames;
    Context mCtx;
    View rootView; //Used for refreshing the ListView from displayAllGames()

    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;

    public StatsTab() {
        // Required empty public constructor
    }


    public void setContext(Context ctx) {
        mCtx = ctx;
    }

    private void getHandler() {
        if (dbHandler == null) {
            dbHandler = new DBHandler(mCtx);
        }
        dbHandler.openDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;

        displayAllGames();
        return rootView;
    }


    public void writeGameToDB(int ourPoints, int theirPoints) {
        getHandler();
        dbHandler.addGame(new Game(ourPoints, theirPoints));
        displayAllGames(); //Refresh list
    }

    public void displayAllGames() { //Populate ListView with all past Games from DB
        getHandler();
        Cursor cursor = dbHandler.getAllGames();

        String[] fromFields = new String[]{DBHandler.KEY_ID, DBHandler.KEY_OUR_SCORE, DBHandler.KEY_THEIR_SCORE};
        int[] intoViews = new int[]{0, R.id.cardOurScore, R.id.cardTheirScore};

        SimpleCursorAdapter sca = new SimpleCursorAdapter(mCtx, R.layout.item_layout, cursor, fromFields, intoViews, 0);

        rootView = inflater.inflate(R.layout.stats_tab, container, false);
        pastGames = (ListView) rootView.findViewById(R.id.pastGames);


        pastGames.setAdapter(sca);
        sca.notifyDataSetChanged();

    }
}
