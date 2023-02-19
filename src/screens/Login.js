import React from 'react';
import TextButton from '../components/atoms/TextButton';
import ImageButton from '../components/atoms/ImageButton';
import styled from 'styled-components/native';
import kakaoLoginImage from '../../assets/kakao.png';
import emailLoginImage from '../../assets/email.png';

const Container = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;

const TextButtonContainer = styled.View`
    flex-direction: row;
    justify-content: center;
    align-items: center;
`;

const LoginButton = styled(ImageButton)`
    margin: 5px 0;
`;

const Login = ({ navigation }) => {
    return (
        <Container>
            <LoginButton
                source={kakaoLoginImage}
                width="200px"
                height="40px"
                onPress={() => navigation.navigate('Kakao')}
            />
            <LoginButton
                source={emailLoginImage}
                width="200px"
                height="40px"
                onPress={() => navigation.navigate('Email')}
            />
            <TextButtonContainer>
                <TextButton title="회원가입" onPress={() => navigation.navigate('EmailRegister')} />
                <TextButton title="ID, PW 찾기" onPress={() => alert('준비 중인 기능입니다.')} />
            </TextButtonContainer>
        </Container>
    );
};

export default Login;
