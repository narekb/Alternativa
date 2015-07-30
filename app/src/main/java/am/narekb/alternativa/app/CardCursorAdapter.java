package am.narekb.alternativa.app;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.widget.SimpleCursorAdapter;

public class CardCursorAdapter extends SimpleCursorAdapter{

    private LayoutInflater inflater;
    public CardCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }


}
