# React Native Sms User Consent

## One-tap SMS verification with the SMS User Consent API
The SMS User Consent API complements the SMS Retriever API by allowing an app to prompt the user to grant access to the content of a single SMS message. When a user gives consent, the app will then have access to the entire message body to automatically complete SMS verification.

## Getting Started


### Install

```
$ yarn add react-native-sms-user-consent
```

### Link

- **React Native 0.60+**


[CLI autolink feature](https://github.com/react-native-community/cli/blob/master/docs/autolinking.md) links the module while building the app. 


- **React Native <= 0.59**


```bash
$ react-native link react-native-sms-user-consent
```


*Note* For `iOS` using `cocoapods`, run:

```bash
$ cd ios/ && pod install
```

## Usage

### User Flow for SMS User Consent API

<div style="display: flex; flex-direction: row; flex-wrap: nowrap;">
<img src="/assets/images/user-consent-1.png" width=200 height="350" />
<img src="/assets/images/user-consent-2.png" width=200 height="350" />
<img src="/assets/images/user-consent-3.png" width=200 height="350" />
</div>


### Import

```js
import SMSUserConsent from 'react-native-sms-user-consent';
```

### Start listen SMS and get message
```tsx
interface SMSMessage {
  receivedOtpMessage: string
}

getSMSMessage = async () => {
  try {
    const message: SMSMessage = await SMSUserConsent.listenOTP()
  } catch (e) {
    // error
  }
}

```

### Remove listener
```tsx

removeSmsListener = () => {
  try {
    SMSUserConsent.removeOTPListener()
  } catch(e) {
    // error
  }
}

```
