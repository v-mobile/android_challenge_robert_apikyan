package com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view;

/**
 * Created by Robert on 17.09.2017.
 */

public interface MvpViewConsumer<V> {
    void postToView(V mvpView);
}
