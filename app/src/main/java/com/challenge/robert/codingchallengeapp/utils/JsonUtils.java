package com.challenge.robert.codingchallengeapp.utils;

import android.support.annotation.Nullable;

import com.challenge.robert.codingchallengeapp.App;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Robert on 17.09.2017.
 */

public class JsonUtils {
    @Nullable
    public static String read(String path) {
        String json = null;
        try {
            InputStream open = App.getInstance().getAssets().open("json_files/users.json");
            int available = open.available();
            byte[] buffer = new byte[available];
            open.read(buffer);
            open.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
