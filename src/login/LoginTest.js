import React, { Component } from 'react';
import {Webview} from 'react-native-webview';
import { Alert } from 'react-native';

const html = `
    <script>
      function send(){
        window.ReactNativeWebView.postMessage('hello!!');
      }
    </script>
    <button onclick="send()">Send</button>
`;

export default class App extends Component {
  render() {
    return (
      <View>
        <WebView style={{top:50}}
          source={{html}}
          onMessage={(event)=> Alert.alert(event.nativeEvent.data) }
        />
      </View>
    );
  }
}
 