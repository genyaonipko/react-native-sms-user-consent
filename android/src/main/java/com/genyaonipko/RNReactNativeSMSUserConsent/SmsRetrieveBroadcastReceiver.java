package com.genyaonipko.RNReactNativeSMSUserConsent;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

public class SmsRetrieveBroadcastReceiver extends BroadcastReceiver {

    public static final int SMS_CONSENT_REQUEST = 1244;

    private Activity activity;

    public SmsRetrieveBroadcastReceiver(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status smsRetrieverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
            int statusCode = smsRetrieverStatus.getStatusCode();
            switch (statusCode) {
                case CommonStatusCodes.SUCCESS:
                    // Get consent intent
                   try {
                        Intent consentIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                        this.activity.startActivityForResult(consentIntent, SMS_CONSENT_REQUEST);
                   } catch (ActivityNotFoundException e) {
                       // Handle the exception ...
                   }
                    break;
                case CommonStatusCodes.TIMEOUT:
                    // Time out occurred, handle the error.
                    break;
            }
        }
    }
}
