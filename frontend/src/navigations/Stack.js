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
import ReservationDetail from '../screens/ReservationDetail';
import LoadingContext from '../context/Loading';
import styled from 'styled-components/native';
import { ActivityIndicator } from 'react-native';

const Stack = createStackNavigator();

const LoadingContainer = styled.View`
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    align-items: center;
    justify-content: center;
`;

const StackNavigation = () => {
    const { isLoading } = React.useContext(LoadingContext);

    return (
        <>
            <Stack.Navigator initialRouteName="Home" screenOptions={{ headerShown : false }}>
                <Stack.Screen name="Main" component={Main} options={{ title: 'clean out'}} />
                <Stack.Screen name="Login" component={Login} options={{ title: '로그인' }} />
                <Stack.Screen name="Register" component={Register} options={{ title: '쓰레기 등록' }} />
                <Stack.Screen name="Reservation" component={Reservation} options={{ title: '예약 현황' }} />
                <Stack.Screen name="Kakao" component={Kakao} options={{ title: '카카오 로그인' }} />
                <Stack.Screen name="Email" component={Email} options={{ title: '이메일 로그인' }} />
                <Stack.Screen name="EmailRegister" component={EmailRegister} options={{ title: '회원 가입' }} />
                <Stack.Screen name="Address" component={Address} options={{ title: '주소' }} />
                <Stack.Screen name="ReservationDetail" component={ReservationDetail} options={{ title: '예약 정보' }} />
            </Stack.Navigator>
            {isLoading && (
                <LoadingContainer>
                    <ActivityIndicator size="large" color={colors.primary}></ActivityIndicator>
                </LoadingContainer>
            )}
        </>
    );
};

export default StackNavigation;
