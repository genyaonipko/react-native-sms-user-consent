import { NativeModules, Platform } from "react-native";
const { RNReactNativeSMSUserConsent } = NativeModules;

export default class SMSUserConsent {
  static async addMessageListener() {
    return new Promise((resolve, reject) => {
      if (Platform.OS != "ios" && Platform.OS != "android") {
        reject(Platform.OS);
      }

      RNReactNativeSMSUserConsent.addMessageListener();
      return resolve('success')
    });
  }
  static async getOneTimeCode() {
    return new Promise((resolve, reject)=>{
      if ((Platform.OS != 'ios') && (Platform.OS != 'android')) {
        reject(Platform.OS)
      }

      const oneTimeCode = RNReactNativeSMSUserConsent.getOneTimeCode();
      if (oneTimeCode) {
        resolve(oneTimeCode)
      }
    })
  }
}
