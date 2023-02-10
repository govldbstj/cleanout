import React from 'react';
import {Button} from 'react-native';
import styled from 'styled-components/native';

const Container = styled.View`
flex: 1;
justify-content: center;
align-items: center;
`;

const StyledText = styled.Text`
font-size: 30px;
margin-bottom: 10px;
`;

const DetailText = styled.Text`
font-size : 15px;
margin-bottom: 10px;
`;

const Login = ({navigation}) => {

    return(
        <Container>
            <StyledText>Login page</StyledText>
            <Button
            title = "카카오 로그인"
            onPress = {()=> navigation.navigate('Kakao')}/>
            <Button
            title = "이메일 로그인"
            onPress = {()=> navigation.navigate('Email')}/>
            <DetailText>회원가입       ID, PW 찾기</DetailText>  
        </Container>
    )
}

export default Login;