import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';
import {Pressable} from 'react-native';

/**
 * 기본 텍스트 버튼 atom (배경 없는 버튼)
 * 필요한 경우 props 추가해서 사용
 * @param {string} title 버튼에 들어갈 글자
 * @param {function} onPress 버튼 클릭 시 실행할 함수
 * @param {string?} fontSize 버튼 글자 크기(기본: 15px)
 */
export default Button = (props) => {
    const { title, onPress, fontSize = "15px" } = props;
    return (
        <BoxPressable onPress={onPress}>
            <ButtonLabel fontSize={fontSize}>{title}</ButtonLabel>
        </BoxPressable>
    );
};

const BoxPressable = styled.Pressable`
    padding: 10px;
`;

const ButtonLabel = styled.Text`
    font-size: ${(props) => props.fontSize};
    text-align: center;
`;
