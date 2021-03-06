package com.example.android.sunshine.app.gcm;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by lian_ on 2016/9/30.
 */

public class MyInstanceIDListenerService extends InstanceIDListenerService {

    private static final String LOG_TAG = MyInstanceIDListenerService.class.getSimpleName();

    /**
     * Called if instanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token.
        Log.i(LOG_TAG, "Refreshed Token");
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
