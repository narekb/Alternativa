package am.narekb.alternativa.app;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import am.narekb.alternativa.R;
import am.narekb.alternativa.db.DBHandler;

public class StatsTab extends Fragment {

    DBHandler dbHandler;
    ListView pastGames;
    View rootView; //Used for refreshing the ListView from displayAllGames()
    Context mCtx;
    TextView rowId;

    CardCursorAdapter cca;

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
        getHandler();
        Cursor cursor = dbHandler.getAllGames();

        // String[] fromFields = new String[]{DBHandler.KEY_ID, DBHandler.KEY_OUR_SCORE, DBHandler.KEY_THEIR_SCORE, DBHandler.KEY_OUR_NAME, DBHandler.KEY_THEIR_NAME};
        // int[] intoViews = new int[]{R.id.rowId, R.id.cardOurScore, R.id.cardTheirScore, R.id.cardOurName, R.id.cardTheirName};

        cca = new CardCursorAdapter(mCtx, R.layout.item_layout, cursor, 0);

        rootView = inflater.inflate(R.layout.stats_tab, container, false);

        pastGames = (ListView) rootView.findViewById(R.id.pastGames);
        pastGames.setAdapter(cca);
        update();

        return rootView;
    }

    public void update() {
        cca.swapCursor(dbHandler.getAllGames());
    }
}
