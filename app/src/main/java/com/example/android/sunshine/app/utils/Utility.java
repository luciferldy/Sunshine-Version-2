package com.example.android.sunshine.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Lucifer on 2016/9/24.
 */

public class Utility {

    /**
     * return true if network is available or about to become to available.
     * @param c Context used to get the ConnectivityManager.
     * @return
     */
    static public boolean isNetWorkAvailable(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
