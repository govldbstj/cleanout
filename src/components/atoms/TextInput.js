import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';

/**
 * 기본 텍스트 입력 atom
 * 필요한 경우 props 추가해서 사용
 * @param {string} placeholder 텍스트 입력창에 표시될 텍스트
 * @param {string?} value 텍스트 입력창의 초기값
 * @param {boolean?} isPassword 텍스트 입력창의 값이 비밀번호인지 여부(기본: false)
 * @param {boolean?} disabled 텍스트 입력창의 활성화 여부(기본: false)
 * @param {string?} fontSize 텍스트 입력창의 폰트 크기(기본: 15px)
 * @param {function?} validator 텍스트 입력창의 유효성 검사 함수((string) => bool)
 * @param {string?} invalidateMessage 유효성 검사 실패 시 표시할 메시지
 * @param {function?} onTextChangeListener 텍스트 입력창의 값이 변경될 때 실행할 함수((string) => void)
 */
export default TextInput = (props) => {
    const {
        placeholder,
        value = '',
        isPassword = false,
        disabled = false,
        fontSize = '15px',
        validator = (_) => null,
        invalidateMessage,
        onTextChangeListener = (_) => {},
        ...others
    } = props;
    const [validate, setValidate] = React.useState(null);

    const onTextChange = (text) => {
        onTextChangeListener(text);
        setValidate(validator(text));
    };

    const borderColor = validate === null ? colors.border : validate ? colors.success : colors.alert;

    return (
        <TextInputContainer {...others}>
            <StyledTextInput
                fontSize={fontSize}
                borderColor={borderColor}
                defaultValue={value}
                secureTextEntry={isPassword}
                disabled={disabled}
                placeholder={placeholder}
                placeholderTextColor={colors.disabled}
                onChangeText={onTextChange}
            ></StyledTextInput>
            <InfoMessage visible={validate === false && !!invalidateMessage}>{invalidateMessage || ''}</InfoMessage>
        </TextInputContainer>
    );
};

const TextInputContainer = styled.View`
    align: left;
`;

const StyledTextInput = styled.TextInput`
    padding: 5px;
    border-bottom-width: 1px;
    font-size: ${(props) => props.fontSize};
    border-color: ${(props) => props.borderColor};
`;

const InfoMessage = styled.Text`
    font-size: 10px;
    color: ${colors.alert};
    margin: 2px 0 0 5px;
    opacity: ${(props) => (props.visible ? 1 : 0)};
`;
