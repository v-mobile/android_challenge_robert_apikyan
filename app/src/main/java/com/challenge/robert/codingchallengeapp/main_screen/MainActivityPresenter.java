package com.challenge.robert.codingchallengeapp.main_screen;

import com.challenge.robert.codingchallengeapp.main_screen.pojos.User;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.MvpPresenter;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpViewConsumer;
import com.challenge.robert.codingchallengeapp.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 17.09.2017.
 */

public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    private List<User> userInfoList;

    MainActivityPresenter() {
        userInfoList = new ArrayList<>();
    }

    void getUsers() {
        if (userInfoList.isEmpty()) {
            String usersJson = JsonUtils.read("json_files/users.json");
            parseUsersJson(usersJson);
        }
        post(new MvpViewConsumer<MainActivityView>() {
            @Override
            public void postToView(MainActivityView mvpView) {
                mvpView.onGetUsersSuccess(userInfoList);
            }
        });
    }

    void addUser(String firstName,String lastName) {
        if (firstName.isEmpty() || lastName.isEmpty()) return;

        final User user = getFormattedUser(firstName,lastName);

        userInfoList.add(user);

        post(new MvpViewConsumer<MainActivityView>() {
            @Override
            public void postToView(MainActivityView mvpView) {
                mvpView.onAddUserSuccess(user);
            }
        });
    }

    private void parseUsersJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String firstName = jsonObject.getString(User.JSON_KEY_FIRST_NAME);
                String lastName = jsonObject.getString(User.JSON_KEY_LAST_NAME);
                userInfoList.add(getFormattedUser(firstName,lastName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private User getFormattedUser(String firstName, String lastName) {
        return new User(formatName(firstName),formatName(lastName));
    }

    private String formatName(String name) {
        String formattedName = "";
        if (!name.isEmpty()) {
            formattedName = String.format("%s%s",name.substring(0, 1).toUpperCase(),name.length() > 1 ? name.substring(1):"");
        }
        return formattedName;
    }
}