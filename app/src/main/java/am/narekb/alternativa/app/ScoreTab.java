package am.narekb.alternativa.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import am.narekb.alternativa.R;

public class ScoreTab extends Fragment {

    int ourPoints = 0, theirPoints = 0;
    TextView ourScore;
    TextView theirScore;

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
        return rootView;
    }

    public void onClick(View v) {
        //TODO: Set up both plus-sign onClicks to this method. Switch case through v.getId() and add scores accordingly
    }


}
