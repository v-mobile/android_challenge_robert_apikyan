package com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter;

import android.support.annotation.NonNull;

import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpView;

/**
 * Created by Robert on 17.09.2017.
 */

public interface PresenterFactory<V extends MvpView,P extends Presenter<V>> {
    /**
     * Called inside {@link PresenterLoader#onForceLoad()}
     * @return Non null presenter
     */
    @NonNull
    P createPresenter();
}
