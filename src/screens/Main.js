import React from 'react';
import Button from '../components/atoms/Button';
import styled from 'styled-components/native';

const Container = styled.View`
align-items: center;
`;
const StyledText = styled.Text`
font-size : 30px;
margin-bottom: 10px;
`;

const Main = ({ navigation }) => {
    return(
        <Container>
            <StyledText>main page</StyledText>
            <Button 
            title = "로그인" 
            onPress = {()=> navigation.navigate('Login')}/>
            <Button
            title = "쓰레기 등록현황"
            onPress = {()=> navigation.navigate('Register')}/>
            <Button
            title = "나의 예약 현황"
            onPress = {()=> navigation.navigate('Reservation')}/>
        </Container>
    )
}

export default Main;