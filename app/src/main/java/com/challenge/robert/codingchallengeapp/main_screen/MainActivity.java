package com.challenge.robert.codingchallengeapp.main_screen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.challenge.robert.codingchallengeapp.R;
import com.challenge.robert.codingchallengeapp.main_screen.adapter.UsersAdapter;
import com.challenge.robert.codingchallengeapp.main_screen.pojos.User;
import com.challenge.robert.codingchallengeapp.main_screen.user_dialog.UserInputDialog;
import com.challenge.robert.codingchallengeapp.mvp.mvp_controllers.MvpActivity;

import java.util.List;

public class MainActivity extends MvpActivity<MainActivityView, MainActivityPresenter>
        implements MainActivityView, MainActivityCallbacks {

    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_main_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter = new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (usersAdapter.isEmpty()) {
            getPresenter().getUsers();
        }
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void onGetUsersSuccess(List<User> userList) {
        usersAdapter.addUsersAndNotify(userList);
    }

    @Override
    public void onAddUserSuccess(User user) {
        // close dialog
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(UserInputDialog.TAG);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
        usersAdapter.addUserAndNotify(user);
    }

    @Override
    public void onAddUserFailed() {
        Toast.makeText(this, R.string.user_name_is_not_valid, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserAlreadyAdded() {
        Toast.makeText(this, R.string.user_already_added, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserAdded(String userName) {
        getPresenter().addUser(userName);
    }

    public void onFabClick(View view) {
        UserInputDialog.newInstance().show(getSupportFragmentManager(), UserInputDialog.TAG);
    }
}
