import React from 'react';
import TextInput from '../atoms/TextInput';
import Label from '../atoms/Label';
import Button from '../atoms/Button';
import styled from 'styled-components/native';

/**
 * 라벨과 텍스트 인풋, 필요한 경우 버튼을 포함하는 컴포넌트
 * @param {string} label 라벨
 * @param {string} placeholder 텍스트 인풋의 플레이스 홀더
 * @param {string?} value 텍스트 인풋의 값
 * @param {function?} validator 텍스트 인풋의 값이 유효한지 검사하는 함수((string) => bool)
 * @param {string?} invalidateMessage 텍스트 인풋의 값이 유효하지 않을 때 보여줄 메시지
 * @param {string?} buttonLabel 버튼의 라벨
 * @param {function?} onButtonPress 버튼을 눌렀을 때 실행할 함수
 */
export default FormTextInput = (props) => {
    const {
        label,
        placeholder,
        value = '',
        validator = (_) => null,
        invalidateMessage,
        buttonLabel,
        onButtonPress,
        ...others
    } = props;
    return (
        <Container {...others}>
            <FormLabel>{label}</FormLabel>
            <InputContainer>
                <FormInput
                    placeholder={placeholder}
                    value={value}
                    validator={validator}
                    invalidateMessage={invalidateMessage}
                />
                {buttonLabel && <FormButton title={buttonLabel} onPress={onButtonPress} />}
            </InputContainer>
        </Container>
    );
};

const Container = styled.View`
    width: 90%;
    display: flex;
    flex-direction: column;
    align-items: stretch;
`;

const InputContainer = styled.View`
    width: 100%;
    display: flex;
    flex-direction: row;
    align-items: top;
`;

const FormLabel = styled(Label)`
    font-weight: bold;
`;

const FormInput = styled(TextInput)`
    flex: 1;
`;

const FormButton = styled(Button)`
    margin-left: 10px;
`;
