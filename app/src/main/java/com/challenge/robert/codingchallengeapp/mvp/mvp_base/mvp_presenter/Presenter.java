package com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_presenter;

import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_view.MvpView;
import com.challenge.robert.codingchallengeapp.mvp.mvp_base.mvp_delegate.MvpViewControllerDelegateImpl;

/**
 * Created by Robert on 17.09.2017.
 */

public interface Presenter<V extends MvpView> {
    /**
     * Called from {@link MvpViewControllerDelegateImpl}
     * @param view controller view (Activity / Fragment)
     */
    void onAttach(V view);

    /**
     * Called from {@link MvpViewControllerDelegateImpl}
     */
    void onDetach();

    /**
     * Called from {@link PresenterLoader#onReset()}
     */
    void onDestroy();
}
