import React, { useState } from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../../components/molecules/FormTextInput';
import Button from '../../components/atoms/Button';
import LoginContext from '../../context/Login';
import { emailSignIn } from '../../controllers/LoginController';
import LoadingContext from '../../context/Loading';

const Container = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;

const ButtonContainer = styled.View`
    flex-direction: row;
    justify-content: center;
    align-items: center;
`;

const BottomButton = styled(Button)`
    margin: 5px;
`;

const Email = ({ navigation }) => {
    const [input, setInput] = useState({
        email: '',
        password: '',
    });
    const { dispatch } = React.useContext(LoginContext);
    const { setIsLoading } = React.useContext(LoadingContext);

    const SetandSent = async () => {
        setIsLoading(true);
        const result = await emailSignIn(input.email, input.password);

        if (result.isSuccess()) {
            dispatch(true);
            alert('로그인에 성공하였습니다.');
            navigation.navigate('Main');
        } else {
            if (result.isInAppFailure()) {
                alert(result.tryGetErrorMessage());
            } else {
                alert('로그인에 실패하였습니다. 다시 시도해주세요.');
            }
        }
        setIsLoading(false);
    };

    return (
        <Container>
            <FormTextInput
                label="이메일"
                placeholder="이메일을 입력하세요."
                onTextChangeListener={(text) => {
                    setInput({ ...input, email: text });
                }}
            />
            <FormTextInput
                label="비밀번호"
                placeholder="비밀번호를 8자 이상 입력하세요."
                isPassword={true}
                onTextChangeListener={(text) => {
                    setInput({ ...input, password: text });
                }}
            />
            <ButtonContainer>
                <BottomButton title="로그인" onPress={() => SetandSent()} />
                <BottomButton title="회원 가입" onPress={() => navigation.navigate('EmailRegister')} />
            </ButtonContainer>
        </Container>
    );
};

export default Email;
