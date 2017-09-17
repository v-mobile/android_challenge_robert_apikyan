package com.challenge.robert.codingchallengeapp.main_screen;

import com.challenge.robert.codingchallengeapp.main_screen.pojos.User;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpView;

import java.util.List;

/**
 * Created by Robert on 17.09.2017.
 */

interface MainActivityView extends MvpView {
    void onGetUsersSuccess(List<User> userList);

    void onAddUserSuccess(User user);

}
