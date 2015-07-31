package am.narekb.alternativa.app;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import am.narekb.alternativa.R;
import am.narekb.alternativa.db.DBHandler;


public class CardCursorAdapter extends CursorAdapter {

    Context ctx;
    private LayoutInflater inflater;
    DBHandler dbHandler;
    int layoutResource;
    ListView parentLv;

    public CardCursorAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, c, flags);
        ctx = context;
        inflater = LayoutInflater.from(context);
        layoutResource = layout;
        getHandler();
    }

    private void getHandler() {
        if (dbHandler == null) {
            dbHandler = new DBHandler(ctx);
        }
        dbHandler.openDB();
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = inflater.inflate(layoutResource, parent, false);
        ViewHolder holder = new ViewHolder();

        parentLv = (ListView)parent;

        holder.setTvRowId((TextView)convertView.findViewById(R.id.rowId));
        holder.setTvOurName((TextView)convertView.findViewById(R.id.cardOurName));
        holder.setTvTheirName((TextView) convertView.findViewById(R.id.cardTheirName));
        holder.setTvOurScore((TextView) convertView.findViewById(R.id.cardOurScore));
        holder.setTvTheirScore((TextView) convertView.findViewById(R.id.cardTheirScore));
        holder.setDeleteButton((ImageView) convertView.findViewById(R.id.cardDeleteButton));

        convertView.setTag(holder);

        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder vh = (ViewHolder) view.getTag();

        String rowId = cursor.getString(cursor.getColumnIndex(DBHandler.KEY_ID));
        String ourName = cursor.getString(cursor.getColumnIndex(DBHandler.KEY_OUR_NAME));
        String theirName = cursor.getString(cursor.getColumnIndex(DBHandler.KEY_THEIR_NAME));
        String ourScore = cursor.getString(cursor.getColumnIndex(DBHandler.KEY_OUR_SCORE));
        String theirScore = cursor.getString(cursor.getColumnIndex(DBHandler.KEY_THEIR_SCORE));

        vh.getTvRowId().setText(rowId);
        vh.getTvOurName().setText(ourName);
        vh.getTvTheirName().setText(theirName);
        vh.getTvOurScore().setText(ourScore);
        vh.getTvTheirScore().setText(theirScore);
        vh.getDeleteButton().setImageResource(R.drawable.ic_clear_black_18dp);

        vh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.cardDeleteButton) {
                    dbHandler.deleteGame(Integer.parseInt(vh.getTvRowId().getText().toString()));
                    notifyDataSetChanged();
                    swapCursor(dbHandler.getAllGames());
                }

            }
        });
    }

    public class ViewHolder {
        private TextView ourName, theirName, ourScore, theirScore, rowId; //rowId is actually invisible
        private ImageView deleteButton;

        public TextView getTvOurName(){
            return ourName;
        }

        public TextView getTvTheirName() {
            return theirName;
        }

        public TextView getTvOurScore() {
            return ourScore;
        }

        public TextView getTvTheirScore() {
            return theirScore;
        }

        public TextView getTvRowId() {
            return rowId;
        }

        public ImageView getDeleteButton() {
            return deleteButton;
        }

        public void setTvOurName(TextView newTv) {
            ourName = newTv;
        }

        public void setTvTheirName(TextView newTv) {
            theirName = newTv;
        }

        public void setTvOurScore(TextView newTv) {
            ourScore = newTv;
        }

        public void setTvTheirScore(TextView newTv) {
            theirScore = newTv;
        }

        public void setTvRowId(TextView newTv) {
            rowId = newTv;
        }

        public void setDeleteButton(ImageView newIv) {
            deleteButton = newIv;
        }
    }
}
