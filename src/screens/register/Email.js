import React, {useState} from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../../components/molecules/FormTextInput';
import Button from '../../components/atoms/Button';
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

const Email = ({ navigation }) => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const SetandSent = () =>{
        fetch('http://43.200.115.73:8080/login', {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              "Content-Length": 54,
            },
            body: JSON.stringify({
                "email" : email,
                "password" : password
            })
        })
    };

    return (
        <Container>
            <StyledText>Email Login page</StyledText>
            <FormTextInput 
                label="이메일"
                placeholder="이메일을 입력하세요." 
                onTextChangeListener={(text) => {
                    setEmail(text)
                }}
            />
            <FormTextInput 
                label="비밀 번호" 
                placeholder="비밀 번호를 입력하세요." 
                isPassword={true}
                onTextChangeListener={(text) => {
                    setPassword(text)
                }}
            />
            <Button title="로그인" onPress={() => SetandSent()}/>
        </Container>
    );
};

export default Email;
