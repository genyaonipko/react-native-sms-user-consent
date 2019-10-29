
package com.genyaonipko.RNReactNativeSMSUserConsent;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Bundle;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;

import java.io.IOException;

public class RNReactNativeSMSUserConsentModule extends ReactContextBaseJavaModule {

    private void addListenerToMessage() {
        SmsRetriever.startSmsUserConsent(null);
    }

    private  void showModal() {
        Intent intent = getCurrentActivity().getIntent();
        String consentIntent = intent.getExtras().getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
        startActivityForResult(consentIntent, SMS_CONSENT_REQUEST)
    }
//
//    private void showHint() {
//        HintRequest hintRequest = new HintRequest.Builder()
//                .setHintPickerConfig(new CredentialPickerConfig.Builder()
//                        .setShowCancelButton(true)
//                        .build())
//                .setPhoneNumberIdentifierSupported(true)
//                .build();
//
//        PendingIntent intent =
//                Auth.CredentialsApi.getHintPickerIntent(mCredentialsApiClient, hintRequest);
//        try {
//            startIntentSenderForResult(intent.getIntentSender(), RC_HINT, null, 0, 0, 0,new Bundle());
//        } catch (IntentSender.SendIntentException e) {
//            Log.e("Login", "Could not start hint picker Intent", e);
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    private final ReactApplicationContext reactContext;
//
//    public RNReactNativeSMSUserConsentModule(ReactApplicationContext reactContext) {
//        super(reactContext);
//        this.reactContext = reactContext;
//    }
//
//    @Override
//    public String getName() {
//        return "RNReactNativeSMSUserConsent";
//    }
//
//    private static final int CREDENTIAL_PICKER_REQUEST = 1;    // Set to an unused request code
//    private static String oneTimeCode = "";
//
//    // Construct a request for phone numbers and show the picker
//    private void requestHint() throws IntentSender.SendIntentException {
//        HintRequest hintRequest = new HintRequest.Builder()
//                .setPhoneNumberIdentifierSupported(true)
//                .build();
//        PendingIntent intent = Credentials.getClient(this).getHintPickerIntent(hintRequest);
//        startIntentSenderForResult(intent.getIntentSender(),
//                RESOLVE_HINT, null, 0, 0, 0);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case CREDENTIAL_PICKER_REQUEST:
//                // Obtain the phone number from the result
//                if (resultCode == Activity.RESULT_OK) {
//                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
//                    // credential.getId();  <-- will need to process phone number string
//                }
//                break;
//            case SMS_CONSENT_REQUEST:
//                if (resultCode == Activity.RESULT_OK) {
//                    // Get SMS message content
//                    String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
//                    // Extract one-time code from the message and complete verification
//                    // `sms` contains the entire text of the SMS message, so you will need
//                    // to parse the string.
//                    oneTimeCode = parseOneTimeCode(message);         // define this function
//
//                    // send one time code to the server
//                } else {
//                    // Consent canceled, handle the error ...
//                }
//                break;
//        }
//    }
//
//    Task<Void> task = SmsRetriever.getClient(context).startSmsUserConsent(senderPhoneNumber /* or null */);
//
//    private static final int SMS_CONSENT_REQUEST = 2;  // Set to an unused request code
//    private final BroadcastReceiver smsVerificationReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
//                Bundle extras = intent.getExtras();
//                Status smsRetrieverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
//
//                switch (smsRetrieverStatus.getStatusCode()) {
//                    case CommonStatusCodes.SUCCESS:
//                        // Get consent intent
//                        Intent consentIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
//                        try {
//                            // Start activity to show consent dialog to user, activity must be started in
//                            // 5 minutes, otherwise you'll receive another TIMEOUT intent
//                            startActivityForResult(consentIntent, SMS_CONSENT_REQUEST);
//                        } catch (ActivityNotFoundException e) {
//                            // Handle the exception ...
//                        }
//                        break;
//                    case CommonStatusCodes.TIMEOUT:
//                        // Time out occurred, handle the error.
//                        break;
//                }
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // ...
//
//        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
//        registerReceiver(smsVerificationReceiver, intentFilter);
//    }
//
//    @ReactMethod
//    public void getOneTimeCode(final Callback callback) {
//        if (oneTimeCode.length() != 0) {
//            callback.invoke(null, oneTimeCode);
//        } else {
//            callback.invoke(0, null);
//        }
//    }

}
