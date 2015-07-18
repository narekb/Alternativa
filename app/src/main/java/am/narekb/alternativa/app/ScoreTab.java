package am.narekb.alternativa.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import am.narekb.alternativa.R;

public class ScoreTab extends Fragment implements View.OnClickListener {

    int ourPoints = 0;
    int theirPoints = 0;

    TextView ourScore;
    TextView theirScore;

    TextView addUs;
    TextView addThem;

    Button resetButton;

    StatsTab mStatsTab; //Keep instance of the stats tab by calling setStatsTab(). Games will be added through this.

    public ScoreTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.score_tab, container, false);
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

    public void setStatsTab(StatsTab tab) {
        mStatsTab = tab;
    } //Is called in TabPagerAdapter.getItem()

    public void onClick(View v) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        //Always use getActivity().getSupportFragmentManager() from inside a Fragment, because Fragments can't get Fragment Managers

        ScoreDialog scoreDialog = new ScoreDialog();
        scoreDialog.setScoreFragment(this);

        if (v.getId() == R.id.add_us) {
            scoreDialog.setWhom("us");
            scoreDialog.show(fm, "Score dialog");
        }
        else if (v.getId() == R.id.add_them) {
            scoreDialog.setWhom("them");
            scoreDialog.show(fm, "Score dialog");
        }
        else if (v.getId() == R.id.reset_button) {
            resetGame();
        }
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
            mStatsTab.writeGameToDB(ourPoints, theirPoints);

            //Reset scores after game is added to DB
            theirPoints = 0;
            ourPoints = 0;

            ourScore.setText("" + ourPoints);
            theirScore.setText("" + theirPoints);
        }
    }

}
