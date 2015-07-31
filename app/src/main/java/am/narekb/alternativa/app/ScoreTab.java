package am.narekb.alternativa.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import am.narekb.alternativa.R;
import am.narekb.alternativa.db.DBHandler;
import am.narekb.alternativa.db.Game;

public class ScoreTab extends Fragment implements View.OnClickListener {

    int ourPoints = 0;
    int theirPoints = 0;

    TextView ourScore;
    TextView theirScore;

    TextView addUs;
    TextView addThem;

    TextView ourLabel;
    TextView theirLabel;

    Button resetButton;

    DBHandler dbHandler;
    FragmentPagerAdapter parent;

    public ScoreTab() {
        // Required empty public constructor
    }

    public void setParent(FragmentPagerAdapter fpa) {
        parent = fpa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.score_tab, container, false);
        ourLabel = (TextView) rootView.findViewById(R.id.our_label);
        ourLabel.setOnClickListener(this);

        theirLabel = (TextView) rootView.findViewById(R.id.their_label);
        theirLabel.setOnClickListener(this);

        ourScore = (TextView) rootView.findViewById(R.id.our_score);
        theirScore = (TextView) rootView.findViewById(R.id.their_score);

        addUs = (TextView) rootView.findViewById(R.id.add_us);
        addUs.setOnClickListener(this);

        addThem = (TextView) rootView.findViewById(R.id.add_them);
        addThem.setOnClickListener(this);

        resetButton = (Button) rootView.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        return rootView;
    }

    private void getHandler() {
        if (dbHandler == null) {
            dbHandler = new DBHandler(getActivity());
        }
        dbHandler.openDB();
    }

    public void onClick(View v) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        //Always use getActivity().getSupportFragmentManager() from inside a Fragment, because Fragments can't get Fragment Managers

        ScoreDialog scoreDialog = new ScoreDialog();
        NameDialog nameDialog = new NameDialog();

        if (v.getId() == R.id.add_us) {
            scoreDialog.setWhom("us");
            scoreDialog.show(fm, "Score dialog");
        }
        else if (v.getId() == R.id.add_them) {
            scoreDialog.setWhom("them");
            scoreDialog.show(fm, "Score dialog");
        }

        else if (v.getId() == R.id.our_label) {
            nameDialog.setWho("our");
            nameDialog.show(fm, "Name dialog");
        }

        else if (v.getId() == R.id.their_label) {
            nameDialog.setWho("their");
            nameDialog.show(fm, "Name dialog");
        }

        else if (v.getId() == R.id.reset_button) {
            resetGame();
        }
    }

    public void changeTeamName(String newName, String target) {
        if (target.equals("our"))
            ourLabel.setText(newName);
        else if (target.equals("their"))
            theirLabel.setText(newName);
    }

    public void changeScore(int newScore, CharSequence whom) {
        if(whom == "us") {
            ourPoints += newScore;
            ourScore.setText("" + ourPoints);
        }
        else {
            theirPoints += newScore;
            theirScore.setText("" + theirPoints);
        }
    }

    public void resetGame() {
        if(ourPoints != 0 || theirPoints != 0) {
            writeGameToDB(ourPoints, theirPoints, ourLabel.getText().toString(), theirLabel.getText().toString());

            //Reset scores after game is added to DB
            theirPoints = 0;
            ourPoints = 0;

            ourScore.setText("" + ourPoints);
            theirScore.setText("" + theirPoints);
        }
    }

    public void writeGameToDB(int ourPoints, int theirPoints, String ourName, String theirName) {
        getHandler();
        dbHandler.addGame(new Game(ourPoints, theirPoints, ourName, theirName));
        parent.notifyDataSetChanged();
    }

}
