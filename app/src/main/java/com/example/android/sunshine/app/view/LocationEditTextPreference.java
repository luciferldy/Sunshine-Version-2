package com.example.android.sunshine.app.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.sunshine.app.R;

/**
 * Created by Lucifer on 2016/9/25.
 */

public class LocationEditTextPreference extends EditTextPreference {

    private static final String LOG_TAG = LocationEditTextPreference.class.getSimpleName();
    private static final int DEFAULT_MINIMUM_LOCATION_LENGTH = 3;
    private int mMinLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.LocationEditTextPreference, 0, 0);
        try {
            mMinLength = a.getInteger(R.styleable.LocationEditTextPreference_minLength, DEFAULT_MINIMUM_LOCATION_LENGTH);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        Log.d(LOG_TAG, "LocationMinimumLength = " + mMinLength);

        return super.onCreateView(parent);
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        final EditText et = getEditText();
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Dialog d = getDialog();
                if (d instanceof AlertDialog) {
                    AlertDialog dialog = (AlertDialog) d;
                    Button positiveBtn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    // Check if the edit text is empty.
                    if (s.length() < mMinLength) {
                        // Disable OK button.
                        positiveBtn.setEnabled(false);
                    } else {
                        // Re-enable the button.
                        positiveBtn.setEnabled(true);
                    }
                }
            }
        });
    }
}
