import { NativeModules, Platform } from "react-native";
const { RNReactNativeSharedGroupPreferences } = NativeModules;

export default class SharedGroupPreferences {
  static async getItem(key, appGroup, inputOptions) {
    return new Promise((resolve, reject) => {
      if (Platform.OS != "ios" && Platform.OS != "android") {
        reject(Platform.OS);
      }

      const options = inputOptions || {};
      RNReactNativeSharedGroupPreferences.getItem(
        key,
        appGroup,
        options,
        (errorCode, item) => {
          if (errorCode != null) {
            reject(errorCode);
          } else {
            resolve(JSON.parse(item));
          }
        }
      );
    });
  }
}
