package am.narekb.alternativa.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import am.narekb.alternativa.R;

public class ScoreTab extends Fragment implements View.OnClickListener {

    int ourPoints = 0, theirPoints = 0;
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
        //Temporarily show Toast notification until NumberPicker dialog is implemented
        if (v.getId() == R.id.add_us)
            Toast.makeText(getActivity(), "Add points to us",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Add points to them",
                    Toast.LENGTH_SHORT).show();

    }


}
