package com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_delegate;

import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpView;

/**
 * Created by Robert on 17.09.2017.
 */

public interface MvpViewControllerDelegate<V extends MvpView> {
    void onCreate();

    void onStart(V mvpView);

    void onStop();
}
