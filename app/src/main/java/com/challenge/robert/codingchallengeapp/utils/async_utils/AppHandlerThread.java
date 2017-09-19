package com.challenge.robert.codingchallengeapp.utils.async_utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by Robert Apikyan on 9/18/2017.
 */

public class AppHandlerThread extends HandlerThread {
    private Handler mainHandler;
    private Handler backgroundThread;

    @MainThread
    public AppHandlerThread(String name) {
        this(name, Process.THREAD_PRIORITY_DEFAULT);
    }

    @MainThread
    public AppHandlerThread(String name, int priority) {
        super(name, priority);
        mainHandler = new Handler();
        start();
        backgroundThread = new Handler(getLooper());
    }

    /**
     * @param asyncTask, will be called inside the background thread queue
     * @param onComplete, will be called after asyncTask execution on the main thread(UI thread)
     * @param <R> the Data type
     */
    public <R> void post(@NonNull final ResultRunnable<R> asyncTask, @NonNull final ResultConsumer<R> onComplete) {
        backgroundThread.post(new Runnable() {
            @Override
            public void run() {
                final R result = asyncTask.get();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onComplete.apply(result);
                    }
                });
            }
        });
    }

    /**
     * @param asyncTask will be called inside the background thread queue
     * @param onComplete will be called after asyncTask execution on the main thread(UI thread)
     */
    public void post(final Runnable asyncTask, final Runnable onComplete) {
        backgroundThread.post(new Runnable() {
            @Override
            public void run() {
                asyncTask.run();
                mainHandler.post(onComplete);
            }
        });
    }
}
