import React from 'react';
import TextButton from '../components/atoms/TextButton';
import Button from '../components/atoms/Button';
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

const TextButtonContainer = styled.View`
    flex-direction: row;
    justify-content: center;
    align-items: center;
`;

const Login = ({ navigation }) => {
    return (
        <Container>
            <StyledText>Login page</StyledText>
            <Button title="카카오 로그인" onPress={() => navigation.navigate('Kakao')} />
            <Button title="이메일 로그인" onPress={() => navigation.navigate('Email')} />
            <TextButtonContainer>
                <TextButton title="회원가입" onPress={() => navigation.navigate('EmailRegister')} />
                <TextButton title="ID, PW 찾기" onPress={() => console.log('ID, PW 찾기 기능 필요')} />
            </TextButtonContainer>
        </Container>
    );
};

export default Login;
