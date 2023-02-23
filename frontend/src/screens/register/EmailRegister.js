import React, { useState, useContext } from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../../components/molecules/FormTextInput';
import Button from '../../components/atoms/Button';
import { emailSignUp } from '../../controllers/LoginController';
import AddressContext, { AddressConsumer } from '../../context/Address';
import LoadingContext from '../../context/Loading';

const Container = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;

const EmailRegister = ({ navigation }) => {
    const [input, setInput] = useState({
        name: '',
        email: '',
        address: '',
        info: '',
        password: '',
        passwordRemind: '',
    });

    const { address } = useContext(AddressContext);
    const { setIsLoading } = useContext(LoadingContext);

    const SetandSent = async () => {
        setIsLoading(true);
        const result = await emailSignUp(
            input.name,
            input.email,
            address,
            input.info,
            input.password,
            input.passwordRemind
        );

        if (result.isSuccess()) {
            alert('가입에 성공하였습니다.');
            navigation.pop();
        } else {
            if (result.isInAppFailure()) {
                alert(result.tryGetErrorMessage());
            } else {
                alert('가입에 실패하였습니다. 다시 시도해주세요.');
            }
        }
        setIsLoading(false);
    };

    return (
        <Container>
            <FormTextInput
                label="이름"
                placeholder="이름을 입력하세요."
                onTextChangeListener={(text) => {
                    setInput({ ...input, name: text });
                }}
            />
            <FormTextInput
                label="이메일"
                placeholder="이메일을 입력하세요."
                onTextChangeListener={(text) => {
                    setInput({ ...input, email: text });
                }}
            />
            <FormTextInput
                label="주소"
                placeholder="검색 버튼을 눌러 주소를 입력하세요."
                disabled={true}
                value={address}
                buttonLabel="검색"
                onButtonPress={() => {
                    navigation.navigate('Address');
                }}
            />
            <FormTextInput
                label="상세 주소"
                placeholder="상세 주소를 입력하세요. (동, 호수 등)"
                onTextChangeListener={(text) => {
                    setInput({ ...input, info: text });
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
            <FormTextInput
                label="비밀번호 확인"
                placeholder="비밀번호를 똑같이 입력하세요."
                isPassword={true}
                onTextChangeListener={(text) => {
                    setInput({ ...input, passwordRemind: text });
                }}
            />
            <Button title="회원 가입" onPress={() => SetandSent()} />
        </Container>
    );
};

export default EmailRegister;
