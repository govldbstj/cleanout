import React from 'react';
import { WebView } from 'react-native-webview';
import {View} from 'react-native';
import testbutton from '../../login/LoginTest.js'

const getCode = (target: string) => {
    const exp = 'code=';
    const condition = target.indexOf(exp);
    if (condition !== -1) {
      const requestCode = target.substring(condition + exp.length);
      requestToken(requestCode);
    }
  };

const Kakao = ({ navigation }) => {

    const REST_API_KEY = 'b03acf4d052e55620e58641d4e6425bb'
    const REDIRECT_URI = 'http://localhost:3000/auth/kakao/callback'

    const INJECTED_JAVASCRIPT = `window.ReactNativeWebView.postMessage('message from webView')`;

    return (
        <View style={{ flex: 1 }}>
          <WebView
            style={{ flex: 1 }}
            source={{
              uri: `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`,
            }}
            injectedJavaScript={INJECTED_JAVASCRIPT}
            javaScriptEnabled
            onMessage={event => {
              const data = event.nativeEvent.url;
              getCode(data);
            }}
          />
        </View>
    );
};

export default Kakao;
