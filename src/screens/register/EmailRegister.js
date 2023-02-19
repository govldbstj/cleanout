import React, { useState, useEffect } from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../../components/molecules/FormTextInput';
import Button from '../../components/atoms/Button';
import * as Api from '../../controllers/ApiController';

const Container = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;

const EmailRegister = ({ navigation }) => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordRemind, setPasswordRemind] = useState('');

    const SetandSent = async () => {
        console.log('EmailRegister:', name, email, password);

        if (name.length === 0) {
            alert('이름을 입력해주세요.');
            return;
        }
        if (email.length === 0) {
            alert('이메일을 입력해주세요.');
            return;
        }
        if (password.length < 8) {
            alert('비밀번호를 8자 이상 입력해주세요.');
            return;
        }
        if (password !== passwordRemind) {
            alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return;
        }
        

        const result = await Api.post('signup', {
            body: {
                nickname: name,
                email: email,
                password: password,
            },
        });

        if (result.isSuccess()) {
            alert('가입에 성공하였습니다.');
            navigation.pop();
        } else {
            alert('가입에 실패하였습니다. 다시 시도해주세요.');
            console.log('EmailRegisterError', result.tryGetErrorCode(), result.tryGetErrorMessage());
            setName('');
            setEmail('');
            setPassword('');
        }
    };

    return (
        <Container>
            <FormTextInput
                label="이름"
                placeholder="이름을 입력하세요."
                onTextChangeListener={(text) => {
                    setName(text);
                }}
            />
            <FormTextInput
                label="이메일"
                placeholder="이메일을 입력하세요."
                onTextChangeListener={(text) => {
                    setEmail(text);
                }}
            />
            <FormTextInput
                label="비밀번호"
                placeholder="비밀번호를 8자 이상 입력하세요."
                onTextChangeListener={(text) => {
                    setPassword(text);
                }}
            />
            <FormTextInput
                label="비밀번호 확인"
                placeholder="비밀번호를 똑같이 입력하세요."
                onTextChangeListener={(text) => {
                    setPasswordRemind(text);
                }}
            />
            <Button title="회원 가입" onPress={() => SetandSent()} />
        </Container>
    );
};

export default EmailRegister;
