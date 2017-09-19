package com.challenge.robert.codingchallengeapp.main_screen;

import com.challenge.robert.codingchallengeapp.main_screen.pojos.User;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.MvpPresenter;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpViewConsumer;
import com.challenge.robert.codingchallengeapp.utils.JsonUtils;
import com.challenge.robert.codingchallengeapp.utils.async_utils.AppHandlerThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 17.09.2017.
 */

public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    private static final String TAG = "MainActivityPresenterl";
    private List<User> userInfoList;
    private AppHandlerThread appHandlerThread;

    MainActivityPresenter() {
        userInfoList = new ArrayList<>();
    }

    /**
     * Checks if users list is empty, then async parse from json,
     * otherwise returns the current users list
     */
    void getUsers() {
        if (userInfoList.isEmpty()) {
            if (appHandlerThread == null) {
                appHandlerThread = new AppHandlerThread(TAG);
            }
            appHandlerThread.post(new Runnable() {
                @Override
                public void run() {
                    // async users loading from json
                    String usersJson = JsonUtils.read("json_files/users.json");
                    parseUsersJson(usersJson);
                }
            }, new Runnable() {
                @Override
                public void run() {
                    // notify after users list is ready
                    postOnGetUserSuccess();
                }
            });
        } else {
            postOnGetUserSuccess();
        }
    }

    /**
     * Add users to users if username is imputed in correct format
     * @param userName imputed userName
     */
    void addUser(String userName) {

        String[] split = userName.replaceAll(" ", "").split(",");

        if (userName.isEmpty() || !userName.contains(",") || split.length < 2 || split[0].isEmpty()) {
            // not valid username
            post(new MvpViewConsumer<MainActivityView>() {
                @Override
                public void postToView(MainActivityView mvpView) {
                    mvpView.onAddUserFailed();
                }
            });
            return;
        }

        // check for unique name
        if (contains(split[0], split[1])) {
            post(new MvpViewConsumer<MainActivityView>() {
                @Override
                public void postToView(MainActivityView mvpView) {
                    mvpView.onUserAlreadyAdded();
                }
            });
            return;
        }

        // username format is ok
        final User user = getFormattedUser(split[0], split[1]);
        userInfoList.add(user);

        post(new MvpViewConsumer<MainActivityView>() {
            @Override
            public void postToView(MainActivityView mvpView) {
                mvpView.onAddUserSuccess(user);
            }
        });
    }

    /**
     * Checks if user is already imputed or not
     * @param firstName Inputted first name
     * @param lastName Inputted second name
     * @return true if user is already added
     */
    private boolean contains(String firstName, String lastName) {
        for (User user :
                userInfoList) {
            if (user.getFirstName().equalsIgnoreCase(firstName.trim())
                    && user.getLastName().equalsIgnoreCase(lastName.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parse the users json and to list
     * NOTE. To use Gson or Jackson library for json parsing is better experience
     * @param json, users json from assets
     */
    private void parseUsersJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String firstName = jsonObject.getString(User.JSON_KEY_FIRST_NAME);
                String lastName = jsonObject.getString(User.JSON_KEY_LAST_NAME);
                userInfoList.add(getFormattedUser(firstName, lastName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private User getFormattedUser(String firstName, String lastName) {
        return new User(formatName(firstName), formatName(lastName));
    }

    /**
     * Make the user name start with uppercase
     */
    private String formatName(String name) {
        String formattedName = "";
        if (!name.isEmpty()) {
            name = name.trim();
            formattedName = String.format("%s%s", name.substring(0, 1).toUpperCase(), name.length() > 1 ? name.substring(1) : "");
        }
        return formattedName;
    }

    private void postOnGetUserSuccess() {
        post(new MvpViewConsumer<MainActivityView>() {
            @Override
            public void postToView(MainActivityView mvpView) {
                mvpView.onGetUsersSuccess(userInfoList);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appHandlerThread.quit();
    }
}
