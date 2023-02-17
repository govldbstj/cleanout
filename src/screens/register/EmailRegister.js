import React, {useState} from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../../components/molecules/FormTextInput';
import Button from '../../components/atoms/Button';

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

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

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
            <Button title="회원 가입" onPress={() => { setPassword(password); setEmail(email); console.log("pwd is", password, "id is ", email);}}/>
        </Container>
    );
};

export default EmailRegister;
