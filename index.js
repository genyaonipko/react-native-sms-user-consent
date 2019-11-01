import { NativeModules, Platform } from "react-native";
const { RNReactNativeSMSUserConsent } = NativeModules;

export default class SMSUserConsent {
  static addMessageListener() {
    return new Promise((resolve, reject) => {
      try {
        if (Platform.OS != "ios" && Platform.OS != "android") {
          reject(Platform.OS);
        }

        RNReactNativeSMSUserConsent.addMessageListener();
        resolve("success");
      } catch (e) {
        reject(e);
      }
    });
  }
  static getOneTimeCode() {
    return new Promise((resolve, reject) => {
      if (Platform.OS != "ios" && Platform.OS != "android") {
        reject(Platform.OS);
      }

      RNReactNativeSMSUserConsent.getOneTimeCode((errorCode, item) => {
        if (errorCode != null) {
          reject(errorCode);
        } else {
          resolve(item);
        }
      });
    });
  }
}
