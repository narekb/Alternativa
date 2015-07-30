package am.narekb.alternativa.app;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import am.narekb.alternativa.R;


public class NameDialog extends DialogFragment {
    EditText label;
    String whose; //"our" or "their"

    public NameDialog() {
        // Empty constructor required for NameFragment
    }

    public void setWho(String target) {
        whose = target;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_name, null);
        label = (EditText) view.findViewById(R.id.team_name);


        return new AlertDialog.Builder(getActivity())
                .setTitle("Edit " + whose + " team name")
                .setView(view)
                .setPositiveButton("Change",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ScoreTab scoreTab = (ScoreTab) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":0");
                                if(label.getText().toString().trim() != "")
                                    scoreTab.changeTeamName(label.getText().toString().trim(), whose);
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
}
