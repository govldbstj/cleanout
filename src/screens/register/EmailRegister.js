import React, {useState, useEffect} from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../../components/molecules/FormTextInput';
import Button from '../../components/atoms/Button';
import qs from 'qs';
import axios from 'axios';

const Container = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;

const StyledText = styled.Text`
    font-size: 30px;
    margin-bottom: 10px;
`;

const EmailRegister = ({ navigation }) => {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const SetandSent = () =>{
        console.log(name, email, password),
        fetch('http://43.200.115.73:8080/signup', {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              "Content-Length": 164,
            },
            body: JSON.stringify({
                "nickname" : name,
                "email" : email,
                "password" : password
            })
        })
    };
    
    return (
        <Container>
            <StyledText>이메일로 회원가입하기</StyledText>
            <FormTextInput 
                label="이름" 
                placeholder="이름을 입력하세요."
                onTextChangeListener={(text) => {
                    setName(text)
                }}
            />
            <FormTextInput 
                label="이메일" 
                placeholder="이메일을 입력하세요."
                onTextChangeListener={(text) => {
                    setEmail(text)
                }}
                />
            <FormTextInput
                label="비밀번호"
                placeholder="비밀번호를 입력하세요."
                validator={(text) => text.length >= 8}
                invalidateMessage="비밀번호는 8자 이상이어야 합니다."
                onTextChangeListener={(text) => {
                    setPassword(text)
                }}
            />
            <FormTextInput
                label="비밀번호 확인"
                placeholder="비밀번호를 입력하세요."
                validator={(text) => text === password}
                invalidateMessage="비밀번호가 일치하지 않습니다."
            />
            <Button title="회원 가입" onPress={() => SetandSent()}/>
        </Container>
    );
};

export default EmailRegister;
