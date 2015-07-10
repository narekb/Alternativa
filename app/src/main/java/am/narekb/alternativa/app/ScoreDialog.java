package am.narekb.alternativa.app;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import am.narekb.alternativa.R;


public class ScoreDialog extends DialogFragment {
    NumberPicker np;
    CharSequence whom; //"us" or "them"

    public ScoreDialog() {
        // Empty constructor required for DialogFragment
    }

    public void setWhom(CharSequence text) {
        whom = text;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_score, null);
        np = (NumberPicker) view.findViewById(R.id.number_picker);
        np.setMinValue(1);
        np.setMaxValue(7);
        np.setWrapSelectorWheel(false);
        return new AlertDialog.Builder(getActivity())
                .setTitle("Add points to " + whom)
                .setView(view)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //getActivity().getSupportFragmentManager().findFragmentById(R.id.pager).changeScore(np.getValue(), whom);
                                //I wish it was that easy
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                        }
                )
                .create();
    }

    /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle("Add points to " + whom);
        View view = inflater.inflate(R.layout.dialog_add_score, container);

        np = (NumberPicker) view.findViewById(R.id.number_picker);
        np.setMinValue(1);
        np.setMaxValue(9);
        np.setWrapSelectorWheel(false);

        return view;
    } */
}