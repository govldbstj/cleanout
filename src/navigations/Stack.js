import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import Login from '../screens/Login';
import Main from '../screens/Main';
import Register from '../screens/Register';
import Reservation from '../screens/Reservation';
import Kakao from '../screens/register/Kakao';
import Email from '../screens/register/Email';
import EmailRegister from '../screens/register/EmailRegister';
import Address from '../screens/Address';

const Stack = createStackNavigator();

const StackNavigation = () => {
    return (
        <Stack.Navigator initialRouteName="Home" screenOptions={{headerBackTitleVisible: false}}>
            <Stack.Screen name="Main" component={Main} options={{title: "(서비스 이름)"}}/>
            <Stack.Screen name="Login" component={Login} options={{title: "로그인"}}/>
            <Stack.Screen name="Register" component={Register} options={{title: "쓰레기 등록"}}/>
            <Stack.Screen name="Reservation" component={Reservation} options={{title: "예약 현황"}}/>
            <Stack.Screen name="Kakao" component={Kakao} options={{title: "카카오 로그인"}}/>
            <Stack.Screen name="Email" component={Email} options={{title: "이메일 로그인"}}/>
            <Stack.Screen name="EmailRegister" component={EmailRegister} options={{title: "회원 가입"}}/>
            <Stack.Screen name="Address" component={Address} options={{title: "주소"}}/>
        </Stack.Navigator>
    );
};

export default StackNavigation;
