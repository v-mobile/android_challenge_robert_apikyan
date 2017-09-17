package com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.Presenter;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.PresenterFactory;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.PresenterLoader;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter.PresenterProvider;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpView;

/**
 * Created by Robert on 17.09.2017.
 */

public class MvpViewControllerDelegateImpl<V extends MvpView, P extends Presenter<V>>
        implements
        MvpViewControllerDelegate<V>,
        LoaderManager.LoaderCallbacks<P>,
        PresenterProvider<V, P> {

    private static final int LOADER_ID = 0x1e112;

    private Context context;
    private LoaderManager loaderManager;
    private PresenterFactory<V, P> presenterFactory;
    private P presenter;

    public MvpViewControllerDelegateImpl(Context context, LoaderManager loaderManager, PresenterFactory<V, P> presenterFactory) {
        this.context = context;
        this.loaderManager = loaderManager;
        this.presenterFactory = presenterFactory;
    }

    @Override
    public void onCreate() {
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public void onStart(V mvpView) {
        if (presenter != null) {
            presenter.onAttach(mvpView);
        }
    }

    @Override
    public void onStop() {
        if (presenter != null) {
            presenter.onDetach();
        }
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(presenterFactory, context);
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        presenter = null;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }
}
