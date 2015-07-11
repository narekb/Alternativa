package am.narekb.alternativa.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import am.narekb.alternativa.R;

public class ScoreTab extends Fragment implements View.OnClickListener {

    int ourPoints = 0;
    int theirPoints = 0;

    TextView ourScore;
    TextView theirScore;

    TextView addUs;
    TextView addThem;

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

        return rootView;
    }

    public void onClick(View v) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        //Always use getActivity().getSupportFragmentManager() from inside a Fragment, because Fragments can't get Fragment Managers

        ScoreDialog scoreDialog = new ScoreDialog();
        scoreDialog.setScoreFragment(this);

        if (v.getId() == R.id.add_us) {
            scoreDialog.setWhom("us");
            scoreDialog.show(fm, "Score dialog");
        }
        else {
            scoreDialog.setWhom("them");
            scoreDialog.show(fm, "Score dialog");
        }
    }

    public void changeScore(int newScore, CharSequence whom) {
        if(whom == "us") {
            ourPoints += newScore;
            ourScore.setText(""+ourPoints);
        }
        else {
            theirPoints += newScore;
            theirScore.setText(""+theirPoints);
        }
    }

}
