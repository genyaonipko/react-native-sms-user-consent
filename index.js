import { NativeModules, Platform } from "react-native";
const { RNReactNativeSMSUserConsent } = NativeModules;

export default class SMSUserConsent {
  static listenOTP() {
    if (Platform.OS != "ios" && Platform.OS != "android") {
      return false;
    }
    return RNReactNativeSMSUserConsent.listenOTP(new Promise((resolve, reject) => {
      try {
        resolve("success");
      } catch (e) {
        reject(e);
      }
    }));

  }
  static removeOTPListener() {
    if (Platform.OS != "ios" && Platform.OS != "android") {
      return false;
    }
    return RNReactNativeSMSUserConsent.removeOTPListener();
  }
}
