package com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter;

import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpView;

/**
 * Created by Robert on 17.09.2017.
 */

public interface PresenterProvider<V extends MvpView,P extends Presenter<V>> {
    P getPresenter();
}
