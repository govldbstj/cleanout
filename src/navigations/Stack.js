import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import Login from '../screens/Login';
import Main from '../screens/Main';
import Register from '../screens/Register';
import Reservation from '../screens/Reservation';
import Kakao from '../register/Kakao';
import Email from '../register/Email';

const Stack = createStackNavigator();

const StackNavigation = () => {
    return (
        <Stack.Navigator initialRouteName = "Home">
            <Stack.Screen name = "Main" component = {Main}/>
            <Stack.Screen name = "Login" component = {Login}/> 
            <Stack.Screen name = "Register" component = {Register}/>
            <Stack.Screen name = "Reservation" component = {Reservation}/>
            <Stack.Screen name = "Kakao" component = {Kakao}/>
            <Stack.Screen name = "Email" component = {Email}/>            
        </Stack.Navigator>
    )
}

export default StackNavigation;

