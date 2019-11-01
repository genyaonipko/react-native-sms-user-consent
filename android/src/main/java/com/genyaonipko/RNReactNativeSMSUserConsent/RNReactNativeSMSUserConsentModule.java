
package com.genyaonipko.RNReactNativeSMSUserConsent;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;


public class RNReactNativeSMSUserConsentModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNReactNativeSMSUserConsentModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        reactContext.addActivityEventListener(activityEventListener);
        SmsRetriever.getClient(reactContext).startSmsUserConsent(null);
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        reactContext.registerReceiver(smsVerificationReceiver, intentFilter);
    }

    @Override
    public String getName() {
        return "RNReactNativeSMSUserConsent";
    }

    private static final int SMS_CONSENT_REQUEST = 2;  // Set to an unused request code
    private final BroadcastReceiver smsVerificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                Status smsRetrieverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

                switch (smsRetrieverStatus.getStatusCode()) {
                    case CommonStatusCodes.SUCCESS:
                        // Get consent intent
                        Intent consentIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                        try {
                            // Start activity to show consent dialog to user, activity must be started in
                            // 5 minutes, otherwise you'll receive another TIMEOUT intent
                            reactContext.startActivityForResult(consentIntent, SMS_CONSENT_REQUEST, null);
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
    };

    private final ActivityEventListener activityEventListener = new ActivityEventListener() {
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
                // ...
                case SMS_CONSENT_REQUEST:
                    if (resultCode == Activity.RESULT_OK) {
                        // Get SMS message content
                        String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                        // Extract one-time code from the message and complete verification
                        // `sms` contains the entire text of the SMS message, so you will need
                        // to parse the string.
//                        String oneTimeCode = parseOneTimeCode(message); // define this function

                        // send one time code to the server
                    } else {
                        // Consent canceled, handle the error ...
                    }
                    break;
            }
        }
    };

    @ReactMethod
    public void getOneTimeCode(Callback callback) {
        String data = smsVerificationReceiver.getResultData();
        int resultCode = smsVerificationReceiver.getResultCode();
        if (resultCode == Activity.RESULT_OK) {
            // Get SMS message content
            String message = data;
            callback.invoke(null, message);
        } else {
            callback.invoke(0, null);
        }
    }
}