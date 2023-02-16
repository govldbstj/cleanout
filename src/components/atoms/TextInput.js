import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';

/**
 * 기본 텍스트 입력 atom
 * 필요한 경우 props 추가해서 사용
 * @param {string} placeholder 텍스트 입력창에 표시될 텍스트
 * @param {string?} fontSize 텍스트 입력창의 폰트 크기(기본: 15px)
 * @param {function?} validator 텍스트 입력창의 유효성 검사 함수((string) => bool)
 * @param {string?} invalidateMessage 유효성 검사 실패 시 표시할 메시지
 */
export default TextInput = (props) => {
    const { placeholder, fontSize = '15px', validator = () => null, invalidateMessage } = props;
    const [validate, setValidate] = React.useState(null);

    const onTextChange = (text) => {
        setValidate(validator(text));
    };

    const borderColor = validate === null ? colors.border : (validate ? colors.success : colors.alert);

    return (
        <TextInputContainer>
            <StyledTextInput
                fontSize={fontSize}
                borderColor={borderColor}
                placeholder={placeholder}
                placeholderTextColor={colors.disabled}
                onChangeText={onTextChange}
            ></StyledTextInput>
            {!validate && !!invalidateMessage && <InfoMessage>{invalidateMessage}</InfoMessage>}
        </TextInputContainer>
    );
};

const TextInputContainer = styled.View`
    align: left;
`;

const StyledTextInput = styled.TextInput`
    padding: 10px;
    border-bottom-width: 1px;
    font-size: ${(props) => props.fontSize};
    border-color: ${(props) => props.borderColor};
`;

const InfoMessage = styled.Text`
    font-size: 10px;
    color: ${colors.alert};
    margin-top: 2px;
`;
