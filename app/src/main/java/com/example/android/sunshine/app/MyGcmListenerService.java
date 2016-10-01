package com.example.android.sunshine.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lian_ on 2016/10/1.
 */

public class MyGcmListenerService  extends GcmListenerService{

    private static final String LOG_TAG = MyGcmListenerService.class.getSimpleName();

    private static final String EXTRA_DATA = "data";
    private static final String EXTRA_WEATHER = "weather";
    private static final String EXTRA_LOCATION = "location";

    public static final int NOTIFICATION_ID = 1;

    /**
     * Called when message is received.
     * @param s from SenderId of the sender
     * @param bundle Data bundle containing message data as key/value pairs. For set of keys use bundle.keySet()
     */
    @Override
    public void onMessageReceived(String s, Bundle bundle) {
        // Time to unparcel the bundle
        if (!bundle.isEmpty()) {
            // TODO: gcm_default sender ID comes from the API console
            String senderId = getString(R.string.gcm_defaultSenderId);
            if (senderId.length() != 0) {
                Toast.makeText(this, "SenderID string needs to be set", Toast.LENGTH_LONG).show();
            }
            // Not a bad idea to check that the message is coming from your server.
            if ((senderId).equals(s)) {
                // process message and then post a notification of the received message.
                try {
                    JSONObject jsonObject = new JSONObject(bundle.getString(EXTRA_DATA));
                    String weather = jsonObject.getString(EXTRA_WEATHER);
                    String location = jsonObject.getString(EXTRA_LOCATION);
                    String alert = String.format(getString(R.string.gcm_weather_alert), weather, location);
                    sendNotification(alert);
                } catch (JSONException e) {
                    // Json parsing failed, so we just let this message go, since GCM is not one of our critical features.
                    Log.e(LOG_TAG, "onMessageReceived", e);
                }
            }
            Log.i(LOG_TAG, "Received: " + bundle.toString());
        }

    }

    /**
     * Put the message into a notification and post it.
     * This is just one simple example of what you might choose to do with a GCM message.
     * @param message The Alert message to be posted.
     */
    private void sendNotification(String message) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        /**
         * Notification using both a large and a small icon (which yours should) need the large icon
         * as a bitmap. So we need to create that here from the resource ID, and pass the object along in our
         * notification builder. Generally, you want to use the app icon as the small icon, so that users understand
         * what app is triggering this notification.
         */
        Bitmap largeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.art_storm);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.art_clear)
                .setLargeIcon(largeIcon)
                .setContentTitle("Weather Alert!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setContentIntent(contentIntent);
        manager.notify(NOTIFICATION_ID, builder.build());

    }


}
