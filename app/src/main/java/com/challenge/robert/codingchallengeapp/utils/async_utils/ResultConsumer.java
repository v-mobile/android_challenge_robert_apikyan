package com.challenge.robert.codingchallengeapp.utils.async_utils;

/**
 * Created by Robert Apikyan on 9/18/2017.
 */

public interface ResultConsumer<R> {
    void apply(R result);
}
