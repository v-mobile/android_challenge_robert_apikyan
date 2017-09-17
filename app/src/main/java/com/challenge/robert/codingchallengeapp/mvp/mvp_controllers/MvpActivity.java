package com.challenge.robert.codingchallengeapp.mvp.mvp_controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_delegate.MvpViewControllerDelegateImpl;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.Presenter;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.PresenterFactory;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.PresenterProvider;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpView;

/**
 * Created by Robert on 17.09.2017.
 */

public abstract class MvpActivity<V extends MvpView, P extends Presenter<V>> extends AppCompatActivity implements PresenterFactory<V, P>,
        MvpView,
        PresenterProvider<V, P> {

    private MvpViewControllerDelegateImpl<V, P> viewControllerDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewControllerDelegate = new MvpViewControllerDelegateImpl<>(this, getSupportLoaderManager(), this);
        viewControllerDelegate.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //noinspection unchecked
        viewControllerDelegate.onStart((V) this);
    }

    @Override
    protected void onStop() {
        viewControllerDelegate.onStop();
        super.onStop();
    }

    @Override
    public P getPresenter() {
        return viewControllerDelegate.getPresenter();
    }
}
