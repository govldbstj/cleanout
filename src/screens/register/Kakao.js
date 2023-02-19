import React from 'react';
import { WebView } from 'react-native-webview';
import { View } from 'react-native';

const getCode = (target, onSuccess) => {
    const exp = 'code=';
    const condition = target.indexOf(exp);
    if (condition !== -1) {
        const requestCode = target.substring(condition + exp.length);
        console.log('in get Code : ', requestCode);
        //requestToken(requestCode);
        onSuccess();
    }
};

const Kakao = ({ navigation }) => {
    const REST_API_KEY = '39b1629ce319fa9f89c3b9f947d92255';
    const REDIRECT_URI = 'http://43.200.115.73:8080/oauth';

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
                onMessage={(event) => {
                    const data = event.nativeEvent.url;
                    getCode(data, () => {
                        alert('로그인에 성공하였습니다.');
                        navigation.popToTop();
                    });
                }}
            />
        </View>
    );
};

export default Kakao;
