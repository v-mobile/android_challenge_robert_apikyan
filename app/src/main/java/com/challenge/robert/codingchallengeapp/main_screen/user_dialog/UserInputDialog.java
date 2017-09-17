package com.challenge.robert.codingchallengeapp.main_screen.user_dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.challenge.robert.codingchallengeapp.R;
import com.challenge.robert.codingchallengeapp.main_screen.MainActivityCallbacks;

/**
 * Created by Robert on 17.09.2017.
 */

public class UserInputDialog extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "UserInputDialog";

    public static UserInputDialog newInstance() {
        return new UserInputDialog();
    }

    private MainActivityCallbacks mainActivityCallbacks;
    private EditText firstNameET;
    private EditText lastNameET;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivityCallbacks = (MainActivityCallbacks) context;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_user_input, null);

        firstNameET = (EditText) dialogView.findViewById(R.id.dialog_user_first_name_et);
        lastNameET = (EditText) dialogView.findViewById(R.id.dialog_user_second_name_et);

        dialogView.findViewById(R.id.dialog_user_add_btn).setOnClickListener(this);

        dialogBuilder.setView(dialogView);
        return dialogBuilder.create();
    }

    @Override
    public void onClick(View view) {
        mainActivityCallbacks.onUserAdded(firstNameET.getText().toString(), lastNameET.getText().toString());
    }
}
