import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';
import {StyleSheet, View, SafeAreaView} from 'react-native';

import { Image } from 'react-native';
import kakaoimg from '../../images/kakao_login_medium_narrow.png';

/**
 * 기본 버튼 atom
 * 필요한 경우 props 추가해서 사용
 * @param {string} title 버튼에 들어갈 글자
 * @param {function} onPress 버튼 클릭 시 실행할 함수
 * @param {string?} backgroundColor 버튼 배경색(기본: colors.primary)
 * @param {string?} fontSize 버튼 글자 크기(기본: 15px)
 */
export default Button = (props) => {

    const { title, onPress, backgroundColor = colors.primary, fontSize = '15px', ...others } = props;
    
    if (title == '카카오 로그인'){
        return(
        <BoxPressable onPress={onPress}>
            <Image source = {kakaoimg}/>
        </BoxPressable>
        )
    }
    else{
        return (
        <BoxPressable backgroundColor={backgroundColor} onPress={onPress} {...others}>
            <ButtonLabel fontSize={fontSize}>{title}</ButtonLabel>
        </BoxPressable>
    ); 
    }
};

const BoxPressable = styled.Pressable`
    padding: 10px;
    background-color: ${(props) => props.backgroundColor};
    border-radius: 5px;
    margin-top : 10px;
`;

const ButtonLabel = styled.Text`
    font-size: ${(props) => props.fontSize};
    text-align: center;
`;

