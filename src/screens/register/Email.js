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

const Email = ({ navigation }) => {

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
                label="비밀 번호" 
                placeholder="비밀 번호를 입력하세요." 
                isPassword={true}
                onTextChangeListener={(text) => {
                    setPassword(text)
                }}
            />
            <Button title="로그인" onPress={() => { setPassword(password); setEmail(email); console.log("pwd is", password, "id is ", email);}}/>
        </Container>
    );
};

export default Email;
