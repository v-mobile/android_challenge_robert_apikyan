package com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter;

import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpView;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpViewConsumer;

/**
 * Created by Robert on 17.09.2017.
 */

public class MvpPresenter<V extends MvpView> implements Presenter<V> {
    private V view;

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void onDestroy() {
    }

    protected final void post(MvpViewConsumer<V> consumer) {
        if (view != null) {
            consumer.postToView(view);
        }
    }
}
