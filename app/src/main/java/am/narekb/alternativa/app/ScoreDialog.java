package am.narekb.alternativa.app;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import am.narekb.alternativa.R;


public class ScoreDialog extends DialogFragment {
    NumberPicker np;
    CharSequence whom; //"us" or "them"
    //ScoreTab mScoreTab;

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
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


        AlertDialog di = new AlertDialog.Builder(getActivity())
                .setTitle("Add points to " + whom)
                .setView(view)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ScoreTab scoreTab = (ScoreTab) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":0");
                                scoreTab.changeScore(np.getValue(), whom);
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

        di.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface di) {
                ((AlertDialog)di).getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.ColorPrimary));
                ((AlertDialog)di).getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.ColorBlack));
            }
        });
        return di;
    }
}